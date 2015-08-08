package com.daexsys.warpl;

import com.google.gson.Gson;
import org.spongepowered.api.world.Location;

import java.util.HashMap;
import java.util.Map;

public class WarpsManager {

    private Map<String, WarpLocation> locations = new HashMap<String, WarpLocation>();

    public void setWarp(String name, Location location) {
        locations.put(name, WarpLocation.convert(location));
    }

    public void removeWarp(String name) {
        locations.remove(name);
    }

    public WarpLocation getWarp(String name) {
        return locations.get(name);
    }

    public String toJson() {
        Gson gson = new Gson();

        return gson.toJson(this);
    }

    public void setLocations(Map<String, WarpLocation> locations) {
        this.locations = locations;
    }

    public Map<String, WarpLocation> getWarps() {
        return locations;
    }
}
