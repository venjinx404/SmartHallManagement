package com.smarthall.model;

import java.util.ArrayList;

public class Hall {

    private String hallName;
    private ArrayList<Room> rooms;

    public Hall() {
        hallName = "Bijoy 24 Hall";
        rooms = new ArrayList<>();

        for (int floor = 1; floor <= 4; floor++) {

            for (int room = 1; room <= 30; room++) {

                String roomNumber = String.valueOf(floor * 100 + room);

                rooms.add(new Room(roomNumber, floor));
            }
        }
    }

    public String getHallName() {
        return hallName;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void displayHallInfo() {
        System.out.println("Hall Name: " + hallName);
        System.out.println("Total Floors: 4");
        System.out.println("Total Rooms: " + rooms.size());
    }
}