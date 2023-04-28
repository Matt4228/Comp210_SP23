package assn07;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below
        String masterPass = "";
        while(!passwordManager.checkMasterPassword(masterPass)) {
            System.out.println("Enter Master Password");
            masterPass = scanner.nextLine();
        }


        boolean exit = false;
        while(!exit) {
            String command = scanner.nextLine();
            switch(command) {
                case "New password":
                    String accnt = scanner.nextLine();
                    String pass = scanner.nextLine();
                    passwordManager.put(accnt, pass);
                    System.out.println("New password added");
                    break;
                case "Get password":
                    accnt = scanner.nextLine();
                    pass = passwordManager.get(accnt);
                    if (pass == null) {
                        System.out.println("Account does not exist");
                    } else {
                        System.out.println(pass);
                    }
                    break;
                case "Delete account":
                    accnt = scanner.nextLine();
                    if(passwordManager.remove(accnt) == null) {
                        System.out.println("Account does not exist");
                    } else {
                        System.out.println("Account deleted");
                    }
                    break;
                case "Check duplicate password":
                    pass = scanner.nextLine();
                    List<String> dupes = new ArrayList<String>();
                    dupes = passwordManager.checkDuplicate(pass);
                    if(dupes.isEmpty()) {
                        System.out.println("No account uses that password");
                    } else {
                        System.out.println("Websites using that password:");
                        for(int i = 0; i < dupes.size(); i++ ) {
                            System.out.println(dupes.get(i));
                        }
                    }
                    break;
                case "Get accounts":
                    Set<String> accnts = passwordManager.keySet();
                    System.out.println("Your accounts:");
                    for (String acct : accnts) {
                        System.out.println(acct);
                    }
                    break;
                case "Generate random password":
                    int length = scanner.nextInt();
                    passwordManager.generateRandomPassword(length);
                    break;
                case "Exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Command not found");

            }
        }
        //New password
        //Get password
        //Delete account
        //Check duplicate password
        //Get accounts
        //Generate random password
        //Exit
    }




}
