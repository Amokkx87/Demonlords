package de.demonlords.igm;

public class OrdnerWechselnEvent {
    private final Integer userId;
    private final Integer ordnerId;

    public OrdnerWechselnEvent(Integer userId, Integer ordnerId) {
        this.userId = userId;
        this.ordnerId = ordnerId;
    }

    public Integer getUserId() { return userId; }
    public Integer getOrdnerId() { return ordnerId; }
}
