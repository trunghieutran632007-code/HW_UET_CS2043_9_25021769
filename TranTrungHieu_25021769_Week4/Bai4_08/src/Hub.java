package Bai4_08.src;

import java.util.*;

public class Hub {
    private List<Device> devices;

    public Hub() {
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void turnOffAll() {
        System.out.println("Turn Off All Devices:");
        for (Device d : devices) {
            d.turnOff();
        }
    }

    public void setupWifi() {
        System.out.println("Setup Wifi:");
        for (Device d : devices) {
            if (d instanceof WifiConnect) {
                ((WifiConnect) d).connectWifi();
            }
        }
    }

}
