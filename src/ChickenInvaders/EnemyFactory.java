package ChickenInvaders;

import java.util.Random;

public class EnemyFactory {

    private static final Random random = new Random();

    public static Enemy createEnemy(int level, int x, int y) {

        switch (level) {

            case 1:
                return new NormalEnemy(x, y, 2);

            case 2:
                return random.nextBoolean()
                        ? new NormalEnemy(x, y, 2)
                        : new FastEnemy(x, y, 2);

            case 3:
                return random.nextBoolean()
                        ? new NormalEnemy(x, y, 3)
                        : new ZigzagEnemy(x, y, 3);

            case 4:
                return new BossEnemy(x, y, false);

            case 5:
                return random.nextBoolean()
                        ? new ShooterEnemy(x, y, 3)
                        : new FastEnemy(x, y, 3);

            case 6:
                return random.nextBoolean()
                        ? new ShooterEnemy(x, y, 4)
                        : new ZigzagEnemy(x, y, 4);

            case 7:

                int r = random.nextInt(4);

                return switch (r) {

                    case 0 -> new NormalEnemy(x, y, 4);
                    case 1 -> new FastEnemy(x, y, 4);
                    case 2 -> new ZigzagEnemy(x, y, 4);
                    default -> new ShooterEnemy(x, y, 4);

                };

            case 8:
                return new BossEnemy(x, y, true);

            default:
                return new NormalEnemy(x, y, 2);

        }
    }
}