package org.example;

import org.voltdb.*;
import org.voltdb.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientFactory.createClient(config);

        try {
            client.createConnection("localhost:32911");
            logger.info("Connected to VoltDB");

            logger.info("Calling the stored procedure...");
            ClientResponse response = client.callProcedure("SelectAllSubscribers");

            logger.info("Procedure called successfully");

            if (response.getStatus() == ClientResponse.SUCCESS) {
                VoltTable resultTable = response.getResults()[0];
                System.out.println("************************************");
                while (resultTable.advanceRow()) {
                    int id = (int) resultTable.getLong("SUBSC_ID");
                    String name = resultTable.getString("SUBSC_NAME");
                    String surname = resultTable.getString("SUBSC_SURNAME");
                    String msisdn = resultTable.getString("MSISDN");
                    int tariffId = (int) resultTable.getLong("TARIFF_ID");
                    long startDate = resultTable.getTimestampAsLong("START_DATE");

                    System.out.println("ID: " + id + ", Name: " + name + ", Surname: " + surname +
                            ", MSISDN: " + msisdn + ", Tariff ID: " + tariffId +
                            ", Start Date: " + startDate);
                }
                System.out.println("************************************");
            } else {
                logger.error("Procedure call failed: " + response.getStatusString());
            }
        } catch (Exception e) {
            logger.error("General exception:", e);
        } finally {
            try {
                client.close();
            } catch (InterruptedException e) {
                logger.error("Interrupted exception during client close:", e);
            }
        }
    }
}
