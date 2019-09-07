package main;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.ClockDao;
import entity.Clock;

public class Menu {

	private ClockDao cDao = new ClockDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> clockOptions = Arrays.asList(
			"brand",
			"type");
	private List<String> tasks = Arrays.asList(
			"Display Clocks",
			"Create Clock",
			"Delete Clock",
			"Update Clock");
	
	public void start() {
		String selection = "";
		do {
			printMenu();
			selection = scanner.nextLine();
			try {
				if(selection.equals("1")) {
					displayClocks();
				} else if (selection.equals("2")) {
					createNewClock();
				} else if (selection.equals("3")) {
					deleteClock();
				} else if (selection.equals("4")) {
					updateClock();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} while (!selection.equals("0"));
	}
		
	private void printMenu() {
		System.out.println("Choose a task:\n********");
		for(int i = 0; i < tasks.size(); i++) {
			System.out.println(i + 1 + "." + tasks.get(i));
		}
	}
	
	private void printMenu2() {
		System.out.println("Choose what to update:\n***********");
		for(int i = 0; i < clockOptions.size(); i++) {
			System.out.println(i + 1 + "." + clockOptions.get(i));
		}
	}
		
	private void displayClocks() throws SQLException {
		List<Clock> clocks = cDao.getClocks();
		for(Clock clock : clocks) {
			System.out.println(clock.getClockId() + " : " + clock.getType() + " : " + clock.getBrand());
		}
	}
	
	private void createNewClock() throws SQLException {
		List<Integer> clockIds = cDao.getClockId();
		System.out.println("Enter new clock ID:");
		int clockId = Integer.parseInt(scanner.nextLine());
		while(clockIds.contains(clockId)) {
			System.out.println("This ID already exists. Please make a unique ID.");
			clockId = Integer.parseInt(scanner.nextLine());
		}
		System.out.println("Enter brief description:");
		String type = scanner.nextLine();
		System.out.println("Enter clock brand:");
		String brand = scanner.nextLine();
		cDao.createClock(clockId, type, brand);
		System.out.println("New clock created!");
	}
		
	private void deleteClock() throws SQLException {
		List<Integer> clockIds = cDao.getClockId();
		System.out.println("Enter clock ID to remove: ");
		int clockId = Integer.parseInt(scanner.nextLine());
		while(!clockIds.contains(clockId)) {
			System.out.println("This ID does not exist. Please type a valid ID.");
			clockId = Integer.parseInt(scanner.nextLine());
		}
		cDao.removeClock(clockId);
		System.out.println("Clock deleted.");
	}
	
	private void updateClock() throws SQLException {
		List<Integer> clockIds = cDao.getClockId();
		System.out.println("Enter clock ID to update:");
		int clockId = Integer.parseInt(scanner.nextLine());
		while(!clockIds.contains(clockId)) {
			System.out.println("This ID does not exist. Please type a valid ID.");
			clockId = Integer.parseInt(scanner.nextLine());
		}
		printMenu2();
		String selection = scanner.nextLine();
		while (!clockOptions.contains(selection)) {
			System.out.println("This is an invalid option. Please type one of the listed options.");
			selection = scanner.nextLine();
		}
		System.out.println("Type update:");
		String update = scanner.nextLine();
		cDao.updateClock(clockId, selection, update);
		System.out.println("Clock updated.");
	}
}
