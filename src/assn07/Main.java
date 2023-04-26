package assn07;


import java.util.Scanner;

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
                    System.out.println(pass);
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
                    if(passwordManager.checkDuplicate(pass).size() == 0) {
                        System.out.println("No account uses that password");
                    } else {
                        for(int i = 0; i < passwordManager.checkDuplicate(pass).size(); i++ ) {
                            passwordManager.checkDuplicate(pass).get(i);
                        }
                    }
                    break;
                case "Get accounts":
                    String[] accnts = (String[]) passwordManager.keySet().toArray();
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
