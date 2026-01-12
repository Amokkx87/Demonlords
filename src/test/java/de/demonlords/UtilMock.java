package de.demonlords;

public class UtilMock extends Util {
	
	private static Integer testUserId;

    public static void setUserId(Integer id) {
        testUserId = id;
    }

    public static void clear() {
        testUserId = null;
    }

    public static Integer getUserId() {
        if (testUserId != null) {
            return testUserId;
        }
        return null; // keine echte JSF-Session nötig
    }
}
