package com.hospital;

public class ProcessingSummary {

    private int totalPendingRecords;
    private int criticalCare;
    private int generalCare;
    private int validationFailed;
    private int skippedDuplicate;
    private int successfullyProcessed;

    public int getTotalPendingRecords() {
        return totalPendingRecords;
    }

    public void setTotalPendingRecords(int totalPendingRecords) {
        this.totalPendingRecords = totalPendingRecords;
    }

    public int getCriticalCare() {
        return criticalCare;
    }

    public void setCriticalCare(int criticalCare) {
        this.criticalCare = criticalCare;
    }

    public int getGeneralCare() {
        return generalCare;
    }

    public void setGeneralCare(int generalCare) {
        this.generalCare = generalCare;
    }

    public int getValidationFailed() {
        return validationFailed;
    }

    public void setValidationFailed(int validationFailed) {
        this.validationFailed = validationFailed;
    }

    public int getSkippedDuplicate() {
        return skippedDuplicate;
    }

    public void setSkippedDuplicate(int skippedDuplicate) {
        this.skippedDuplicate = skippedDuplicate;
    }

    public int getSuccessfullyProcessed() {
        return successfullyProcessed;
    }

    public void setSuccessfullyProcessed(int successfullyProcessed) {
        this.successfullyProcessed = successfullyProcessed;
    }
    public void incrementCriticalCare() {
        criticalCare++;
    }

    public void incrementGeneralCare() {
        generalCare++;
    }

    public void incrementValidationFailed() {
        validationFailed++;
    }

    public void incrementSkippedDuplicate() {
        skippedDuplicate++;
    }

    public void incrementSuccessfullyProcessed() {
        successfullyProcessed++;
    }
    public void printSummary() {

        System.out.println();
        System.out.println("PROCESSING SUMMARY");
        System.out.println("----------------------------------------");

        System.out.println(
                "Total Pending Records : "
                + totalPendingRecords);

        System.out.println(
                "Critical Care         : "
                + criticalCare);

        System.out.println(
                "General Care          : "
                + generalCare);

        System.out.println(
                "Validation Failed     : "
                + validationFailed);

        System.out.println(
                "Skipped Duplicate     : "
                + skippedDuplicate);

        System.out.println(
                "Successfully Processed: "
                + successfullyProcessed);
    }
}

