package gq.llamasoftware.twitchexample;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0)
            return;

        new TwitchExample().launch(args[0]);
    }

}