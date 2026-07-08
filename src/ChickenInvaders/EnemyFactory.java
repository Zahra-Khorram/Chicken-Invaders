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
                // غول مرحله ۴: جان ۱۰۰، سرعت ۱.۵، مرحله ۴ (۴ جهته)
                return new BossEnemy(x, y, 100, 1.5, 4);

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
                // غول مرحله ۸: جان ۳۰۰، سرعت ۲.۰، مرحله ۸ (۸ جهته)
                return new BossEnemy(x, y, 300, 2.0, 8);

            default:
                return new NormalEnemy(x, y, 2);

        }
    }

    public static Enemy createEnemyByType(EnemyType type, int x, int y, int level) {

        switch (type) {

            case NORMAL:
                return new NormalEnemy(x, y, level >= 5 ? 4 : 2);

            case FAST:
                return new FastEnemy(x, y, level >= 5 ? 4 : 2);

            case ZIGZAG:
                return new ZigzagEnemy(x, y, level >= 5 ? 4 : 2);

            case SHOOTER:
                return new ShooterEnemy(x, y, level >= 5 ? 4 : 2);

            default:
                return new NormalEnemy(x, y, 2);
        }
    }
}