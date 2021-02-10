Java web application to track expiring food products, devices that require cleaning/part exchange.
First component is a database of templates for food and devices that contains information which is universal, e.g., every mayonnaise after opening can be stored in fridge for 14 days, every toothbrush requires (head) exchange after 90 days.
The second component is a database of actual items which are created from templates. It contains information specific to an item, e.g., expiry date of the mayonaise and its storage/usage status or date on which a toothbrush was exchanged.
Each type of items has specific methods, e.g., clean for devices or open, freeze etc. for food.
A user can add their items and afterwards check the "use by" date for a product, list food items which require usage within certain time, check devices which require attention etc.
TODO: Notifications
TODO: Handle other types of items: medicine, subscriptions. They already exist as templates but are not dealed with as items.

Uses: Apache Maven, Spring, Spring Boot, Spring Data, Hibernate, H2 database, Flyway database migration tool
