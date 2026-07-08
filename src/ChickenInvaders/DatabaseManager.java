package ChickenInvaders;

import java.io.*;
        import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:game.db";
    private static boolean useFallback = false;
    private static final String FALLBACK_FILE = "users_data.txt";


    public static void initDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 Statement stmt = conn.createStatement()) {

                //  ساخت جدول کاربران و تاریخچه بازی ها
                stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                        "username TEXT PRIMARY KEY, " +
                        "password TEXT, " +
                        "high_score INTEGER DEFAULT 0, " +
                        "bg_music INT DEFAULT 1, " +
                        "shot_sound INT DEFAULT 1, " +
                        "crash_sound INT DEFAULT 1, " +
                        "game_over_sound INT DEFAULT 1, " +
                        "current_plane TEXT DEFAULT 'Default')");

                stmt.execute("CREATE TABLE IF NOT EXISTS games (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "username TEXT, " +
                        "score INTEGER, " +
                        "level INTEGER, " +
                        "timestamp TEXT)");

                System.out.println("Database initialized successfully.");
            }
        } catch (Exception e) {
            System.out.println("SQLite driver not found or database error. Switching to Advanced Text File Fallback.");
            useFallback = true;
            initFallbackFile();
        }
    }

    private static void initFallbackFile() {
        File file = new File(FALLBACK_FILE);
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException ignored) {}
        }
    }


    public static boolean registerUser(String username, String password) {
        if (username.trim().isEmpty() || password.trim().isEmpty()) return false;

        if (useFallback) {
            List<User> users = loadUsersFallback();
            for (User u : users) {
                if (u.getUsername().equalsIgnoreCase(username)) return false;
            }
            try (PrintWriter out = new PrintWriter(new FileWriter(FALLBACK_FILE, true))) {

                out.println(username + "," + password + ",0,1,1,1,1,Default");
                return true;
            } catch (IOException e) { return false; }
        } else {
            String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
    }


    public static User loginUser(String username, String password) {
        if (useFallback) {
            List<User> users = loadUsersFallback();
            for (User u : users) {
                if (u.getUsername().equals(username) && u.getPassword().equals(password)) return u;
            }
        } else {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    User u = new User(rs.getString("username"), rs.getString("password"));
                    u.setHighScore(rs.getInt("high_score"));
                    u.setBgMusic(rs.getInt("bg_music") == 1);
                    u.setShotSound(rs.getInt("shot_sound") == 1);
                    u.setCrashSound(rs.getInt("crash_sound") == 1);
                    u.setGameOverSound(rs.getInt("game_over_sound") == 1);
                    u.setCurrentPlane(rs.getString("current_plane"));
                    return u;
                }
            } catch (SQLException ignored) {}
        }
        return null;
    }

    public static void updateUser(User user) {
        if (useFallback) {
            List<User> users = loadUsersFallback();
            try (PrintWriter out = new PrintWriter(new FileWriter(FALLBACK_FILE))) {
                for (User u : users) {
                    if (u.getUsername().equals(user.getUsername())) {
                        out.println(user.getUsername() + "," + user.getPassword() + "," + user.getHighScore() + ","
                                + (user.isBgMusic() ? 1 : 0) + "," + (user.isShotSound() ? 1 : 0) + ","
                                + (user.isCrashSound() ? 1 : 0) + "," + (user.isGameOverSound() ? 1 : 0) + "," + user.getCurrentPlane());
                    } else {
                        out.println(u.getUsername() + "," + u.getPassword() + "," + u.getHighScore() + ","
                                + (u.isBgMusic() ? 1 : 0) + "," + (u.isShotSound() ? 1 : 0) + ","
                                + (u.isCrashSound() ? 1 : 0) + "," + (u.isGameOverSound() ? 1 : 0) + "," + u.getCurrentPlane());
                    }
                }
            } catch (IOException ignored) {}
        } else {
            String sql = "UPDATE users SET high_score=?, bg_music=?, shot_sound=?, crash_sound=?, game_over_sound=?, current_plane=? WHERE username=?";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, user.getHighScore());
                pstmt.setInt(2, user.isBgMusic() ? 1 : 0);
                pstmt.setInt(3, user.isShotSound() ? 1 : 0);
                pstmt.setInt(4, user.isCrashSound() ? 1 : 0);
                pstmt.setInt(5, user.isGameOverSound() ? 1 : 0);
                pstmt.setString(6, user.getCurrentPlane());
                pstmt.setString(7, user.getUsername());
                pstmt.executeUpdate();
            } catch (SQLException ignored) {}
        }
    }


    public static void saveGameRecord(String username, int score, int level) {

        String timestamp = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss"
        ).format(new java.util.Date());


        if (useFallback) {

            try (PrintWriter out = new PrintWriter(
                    new FileWriter("/Users/anahita/IdeaProjects/ChIGame.java/game_history.txt", true))) {

                out.println(username + "," + score + "," + level + "," + timestamp);

            } catch (IOException ignored) {}

        } else {

            try (Connection conn = DriverManager.getConnection(DB_URL)) {

                // ذخیره تاریخچه بازی
                String sql =
                        "INSERT INTO games(username, score, level, timestamp) VALUES(?,?,?,?)";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setString(1, username);
                    pstmt.setInt(2, score);
                    pstmt.setInt(3, level);
                    pstmt.setString(4, timestamp);

                    pstmt.executeUpdate();
                }


                // بروزرسانی رکورد بالاتر کاربر
                String update =
                        "UPDATE users SET high_score = ? " +
                                "WHERE username = ? AND high_score < ?";

                try (PreparedStatement pstmt = conn.prepareStatement(update)) {

                    pstmt.setInt(1, score);
                    pstmt.setString(2, username);
                    pstmt.setInt(3, score);

                    pstmt.executeUpdate();
                }


            } catch (SQLException e) {

                e.printStackTrace();

            }
        }
    }

    // لیست ۱۰ امتیاز برتر برای نمایش در High Scores
    public static List<String[]> getHighScores() {
        List<String[]> list = new ArrayList<>();
        if (useFallback) {

            try (BufferedReader br = new BufferedReader(
                    new FileReader("/Users/anahita/IdeaProjects/ChIGame.java/game_history.txt"))) {

                String line;

                ArrayList<String[]> temp = new ArrayList<>();

                while ((line = br.readLine()) != null) {

                    String[] data = line.split(",");

                    if (data.length >= 4) {
                        temp.add(new String[]{
                                data[0], // username
                                data[1], // score
                                data[2], // level
                                data[3]  // timestamp
                        });
                    }
                }

                temp.sort((a,b) ->
                        Integer.compare(
                                Integer.parseInt(b[1]),
                                Integer.parseInt(a[1])
                        )
                );

                list.addAll(temp.subList(0, Math.min(10, temp.size())));

            } catch(IOException e){
                e.printStackTrace();
            }
        }
        else {
            // استخراج کاربران بر اساس بالاترین امتیاز کسب شده به ترتیب نزولی
            String sql = "SELECT username, high_score, current_plane FROM users WHERE high_score > 0 ORDER BY high_score DESC LIMIT 10";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(new String[]{
                            rs.getString("username"),
                            String.valueOf(rs.getInt("high_score")),
                            rs.getString("current_plane"),
                            "Saved"
                    });
                }
            } catch (SQLException ignored) {}
        }
        return list;
    }

    // متد کمکی برای لود کاربران در حالت فایل متنی
    private static List<User> loadUsersFallback() {
        List<User> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FALLBACK_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 8) {
                    User u = new User(p[0], p[1]);
                    u.setHighScore(Integer.parseInt(p[2]));
                    u.setBgMusic(p[3].equals("1"));
                    u.setShotSound(p[4].equals("1"));
                    u.setCrashSound(p[5].equals("1"));
                    u.setGameOverSound(p[6].equals("1"));
                    u.setCurrentPlane(p[7]);
                    list.add(u);
                }
            }
        } catch (IOException ignored) {}
        return list;
    }
}