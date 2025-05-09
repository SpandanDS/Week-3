public class CircularTour {
    static class PetrolPump {
        int petrol;
        int distance;

        PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    public static int findStartingPt(PetrolPump[] pumps) {
        int n = pumps.length;
        int start = 0, currPetrol = 0, totalPetrol = 0;

        for (int i = 0; i < n; i++) {
            int netGain = pumps[i].petrol - pumps[i].distance;
            currPetrol += netGain;
            totalPetrol += netGain;

            if (currPetrol < 0) {
                start = i + 1;
                currPetrol = 0;
            }
        }
        return totalPetrol >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        PetrolPump[] pumps = {
                new PetrolPump(4, 6),
                new PetrolPump(6, 5),
                new PetrolPump(7, 3),
                new PetrolPump(4, 5)
        };

        int startIndex = findStartingPt(pumps);
        System.out.println("Starting pump index = " + startIndex);
    }
}
