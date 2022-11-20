import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.List;


public class InfinityPond {
    public static void main(String[] args) {
        ArrayList<String> fish = new ArrayList<>(Arrays.asList("M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "F",
                "F", "F", "F", "F", "F", "F", "F", "F", "F"));
        ThreadA t1 = new ThreadA(fish);
        t1.start();
        ThreadA t2 = new ThreadA(fish);
        t2.start();
        ThreadA t3 = new ThreadA(fish);
        t3.start();
        ThreadA t4 = new ThreadA(fish);
        t4.start();
        ThreadA t5 = new ThreadA(fish);
        t5.start();

    }
}
class ThreadA extends Thread {
    ArrayList<String> fishArray;
    public ThreadA(ArrayList<String> fish) {
        this.fishArray = fish;
    }

    public void run() {
        try {
            synchronized (this) {
                Random random = new Random();
                while (!fishArray.isEmpty()) {
                    int i = random.nextInt(fishArray.size());
                    int j = random.nextInt(fishArray.size());
                    while (i == j)
                    j = random.nextInt(fishArray.size());

                    if (fishArray.get(i) == "M" && fishArray.get(j) == "M") {
                        fishArray.remove(fishArray.get(i));
                        fishArray.remove(fishArray.get(j));
                        System.out.println("Two male fish met and they killed each other: " + fishArray);
                        System.out.println("Remaining fish in the pond: "+ fishArray.size());
                    } 

                    else if(fishArray.get(i) == "F" && fishArray.get(j) == "F"){
                        fishArray.remove(fishArray.get(i));
                        System.out.println("Two female fish met and one killed the other: " + fishArray);
                        System.out.println("Remaining fish in the pond: "+ fishArray.size());
                    }

                    else if ((fishArray.get(i) == "M" && fishArray.get(j) == "F") || (fishArray.get(i) == "F" && fishArray.get(j) == "M") ){
                        fishArray.add(fishArray.get(random.nextInt(fishArray.size())));
                        fishArray.add(fishArray.get(random.nextInt(fishArray.size())));
                        System.out.println("A male and female fish met and they spawn two new fish of random gender: " + fishArray);
                        System.out.println("Remaining fish in the pond: "+ fishArray.size());
                    }
                }
                System.exit(1);
            }
        }
        catch(Exception e){}
    }
}
