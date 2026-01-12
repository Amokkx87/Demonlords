package de.demonlords.igm;

public final class IgmFlags {

    private IgmFlags() {}

    public static final int GELESEN = 1;                   // 0b0000_0000_0000_0001
    public static final int ERSTMALIG_GELESEN = 2;         // 0b0000_0000_0000_0010
    public static final int GRUPPEN_IGM = 4;               // 0b0000_0000_0000_0100
    public static final int GELOESCHT_USER = 8;            // 0b0000_0000_0000_1000
    public static final int GELOESCHT_SENDER = 16;         // 0b0000_0000_0001_0000
    public static final int GESICHERT_USER = 32;           // 0b0000_0000_0010_0000
    public static final int TRIBUNAL = 64;                 // 0b0000_0000_0100_0000
    public static final int RUND_IGM = 128;                // 0b0000_0000_1000_0000
    public static final int SITTER_IGM = 256;              // 0b0000_0001_0000_0000
    public static final int TURNIER_IGM = 512;             // 0b0000_0010_0000_0000
    public static final int PRIVATE_SENDER = 1024;         // 0b0000_0100_0000_0000
    public static final int PRIVATE_USER = 2048;           // 0b0000_1000_0000_0000
    public static final int GESICHERT_SENDER = 4096;       // 0b0001_0000_0000_0000
    public static final int ESPIONAGE_IGM = 8192;          // 0b0010_0000_0000_0000
    public static final int TRADE_IGM = 16384;             // 0b0100_0000_0000_0000
    public static final int MJ_IGM = 32768;                // 0b1000_0000_0000_0000
    public static final int PRIVATE_INFO_IGM = 65536;      // 0b0001_0000_0000_0000_0000
    public static final int GROUP_ESPIONAGE_IGM = 131072;  // 0b0010_0000_0000_0000_0000
}
