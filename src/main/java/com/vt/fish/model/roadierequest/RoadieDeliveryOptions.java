package com.vt.fish.model.roadierequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoadieDeliveryOptions {

    public RoadieDeliveryOptions(boolean signatureRequired, boolean notificationsEnabled, boolean over21Required, double extraCompensation) {
        this.signatureRequired = signatureRequired;
        this.notificationsEnabled = notificationsEnabled;
        this.over21Required = over21Required;
        this.extraCompensation = extraCompensation;
    }

    //todo Add to Policy
    @JsonProperty("signature_required")
    private final boolean signatureRequired;

    @JsonProperty("notifications_enabled")
    private final boolean notificationsEnabled;

    @JsonProperty("over_21_required")
    private final boolean over21Required;

    @JsonProperty("extra_compensation")
    private final double extraCompensation;

    public boolean isSignatureRequired() {
        return signatureRequired;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public boolean isOver21Required() {
        return over21Required;
    }

    public double getExtraCompensation() {
        return extraCompensation;
    }
}
