package anet.channel.statist;

/* compiled from: Taobao */
public class StrategyCountObject extends CountObject {
    public static StrategyCountObject get(boolean z) {
        StrategyCountObject strategyCountObject = new StrategyCountObject();
        strategyCountObject.module = "networkPrefer";
        strategyCountObject.modulePoint = "amdc";
        strategyCountObject.arg = z ? "1" : "0";
        return strategyCountObject;
    }
}
