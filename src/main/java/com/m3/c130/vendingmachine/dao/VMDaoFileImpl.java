package com.m3.c130.vendingmachine.dao;

import com.m3.c130.vendingmachine.dto.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class VMDaoFileImpl implements VMDao {
    private final String VM_FILE;
    private static final String DELIMITER = "::";

    Map<Integer, Item> items = new HashMap<>();
    Map<Integer, Integer> stash = new HashMap<>();

    public VMDaoFileImpl() throws PersistenceException {
        VM_FILE = "vm.txt";
        load();
    }

    public VMDaoFileImpl(String file) throws PersistenceException {
        VM_FILE  = file;
        load();
    }

    public Item getItem(int id) {
        return items.get(id);
    }

    public void addItem(Item item, int count) {
        items.put(item.getId(), item);
        if (stash.containsKey(item.getId())) {
            stash.put(item.getId(), stash.get(item.getId())+count);
        } else {
            stash.put(item.getId(), count);
        }
    }

    public List<Item> getItemsList() {
        return new ArrayList<Item>(items.values());
    }

    public int getCount(int id) {
        return stash.get(id);
    }

    public void buyItem(int id) throws NoItemInventoryException, PersistenceException {
        if (stash.containsKey(id) && stash.get(id) > 0) {
            stash.put(id, stash.get(id) - 1);
            save();
        } else {
            throw new NoItemInventoryException("No such item in inventory.");
        }
    }

    private Item unmarshallItem(String text) {
        //itemid::name::cost
        String[] tokens = text.split(DELIMITER);
        Item item = new Item();
        item.setId(Integer.parseInt(tokens[0]));
        item.setName(tokens[1]);
        item.setPrice(Integer.parseInt(tokens[2]));
        return item;
    }

    public static String marshallItem(Item item) {
        return String.join(DELIMITER, Integer.toString(item.getId()), item.getName(), Integer.toString(item.getPrice()));
    }

    private void load() throws PersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(VM_FILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException("Could not load from memory", e);
        }
        String currentLine;
        Item item;
        int count;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            item = unmarshallItem(currentLine);
            currentLine = scanner.nextLine();
            count = Integer.parseInt(currentLine);
            items.put(item.getId(), item);
            stash.put(item.getId(), count);
        }
        scanner.close();
    }

    private void save() throws PersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(VM_FILE));
        } catch (IOException e) {
            throw new PersistenceException("Could not save data", e);
        }

        String text;
        List<Item> itemList = this.getItemsList();
        for (Item item : itemList) {
            text = marshallItem(item);
            out.println(text);
            out.println(stash.get(item.getId()));
            out.flush();
        }
        out.close();
    }
}
