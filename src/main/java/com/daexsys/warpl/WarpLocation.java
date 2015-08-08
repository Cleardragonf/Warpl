package com.daexsys.warpl;

import org.spongepowered.api.world.Location;

public class WarpLocation {

    public double x;
    public double y;
    public double z;

    public WarpLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static WarpLocation convert(Location location) {
        return new WarpLocation(location.getX(), location.getY(), location.getZ());
    }
}
