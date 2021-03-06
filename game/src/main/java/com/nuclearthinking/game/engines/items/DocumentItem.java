package com.nuclearthinking.game.engines.items;

import com.nuclearthinking.game.engines.Base;
import com.nuclearthinking.game.model.StatsSet;
import com.nuclearthinking.game.model.items.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Izonami on 26.12.2015.
 */
public class DocumentItem extends Base {
    private final List<Item> _itemInFile = new ArrayList<>();
    private ItemInfo _currentItem = null;

    public DocumentItem(InputStream file) {
        super(file);
    }

    @Override
    protected StatsSet getStatsSet() {
        return _currentItem.set;
    }

    @Override
    protected String getTableValue(String name) {
        return _tables.get(name)[_currentItem.currentLevel];
    }

    @Override
    protected String getTableValue(String name, int idx) {
        return _tables.get(name)[idx - 1];
    }

    @Override
    protected void parseDocument(Document doc) {
        for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
            if ("list".equalsIgnoreCase(n.getNodeName())) {
                for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
                    if ("item".equalsIgnoreCase(d.getNodeName())) {
                        try {
                            _currentItem = new ItemInfo();
                            parseItem(d);
                            _itemInFile.add(_currentItem.item);
                            resetTable();
                        } catch (Exception e) {
                            LOG.log(Level.WARNING, "Cannot create item " + _currentItem.id, e);
                        }
                    }
                }
            }
        }
    }

    protected void parseItem(Node n) throws InvocationTargetException {
        int itemId = Integer.parseInt(n.getAttributes().getNamedItem("id").getNodeValue());
        String className = n.getAttributes().getNamedItem("type").getNodeValue();
        String itemName = n.getAttributes().getNamedItem("name").getNodeValue();

        _currentItem.id = itemId;
        _currentItem.type = className;
        _currentItem.name = itemName;

        _currentItem.set = new StatsSet();
        _currentItem.set.set("item_id", itemId);
        _currentItem.set.set("name", itemName);

        Node first = n.getFirstChild();
        for (n = first; n != null; n = n.getNextSibling()) {
            if ("set".equalsIgnoreCase(n.getNodeName())) {
                if (_currentItem.item != null) {
                    throw new IllegalStateException("Item created but set node found! Item " + itemId);
                }
                parseBeanSet(n, _currentItem.set, 1);
            }
        }

        makeItem();
    }

    private void makeItem() throws InvocationTargetException {
        if (_currentItem.item != null) {
            return;
        }
        try {
            Constructor<?> c = Class.forName("com.nuclearthinking.game.model.items." + _currentItem.type).getConstructor(StatsSet.class);
            _currentItem.item = (Item) c.newInstance(_currentItem.set);
        } catch (Exception e) {
            throw new InvocationTargetException(e);
        }
    }

    public List<Item> getItemList() {
        return _itemInFile;
    }
}
