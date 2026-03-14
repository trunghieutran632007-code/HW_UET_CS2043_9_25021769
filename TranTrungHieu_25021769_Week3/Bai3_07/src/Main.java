package Bai3_07.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringRoom = scanner.nextLine();
        String[] lstStr = stringRoom.strip().split("\\s+");
        String typeRoom = lstStr[0];
        int nights = Integer.parseInt(lstStr[1]);
    
        if (typeRoom.equalsIgnoreCase("S")) {
            Room curRoom = new StandardRoom(nights);
            System.out.println(curRoom.Pay());
        } else if (typeRoom.equalsIgnoreCase("V")) {
            Room curRoom = new VipRoom(nights);
            System.out.println(curRoom.Pay());
        }

        

    }

}
