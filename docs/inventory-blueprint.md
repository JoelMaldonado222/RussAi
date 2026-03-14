# 🥃 Russ AI Inventory Blueprint: The Hangry Bison

## 📊 Database Schema Definition
| Column | Type | Purpose |
| :--- | :--- | :--- |
| **spirit_id** | UUID | Primary Key |
| **name** | String | Product name (e.g., "Eagle Rare") |
| **category** | String | Spirit type (Bourbon, Tequila, etc.) |
| **price_pour** | Decimal | 2oz Pour Price |
| **distillery** | String | Producing Distillery |
| **flavor_tags** | Text | Comma-separated DNA tags (Vanilla, Oak, etc.) |
| **mash_bill** | Text | Grain recipe (Wheated, High Rye, etc.) |
| **suggested_upsells** | List<UUID> | IDs of 2-5 premium comparable upgrades |

---

## 📑 Initial Seed Data (Verified Research)

| name | price_pour | category | distillery | mash_bill | flavor_tags |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1792 Sweet Wheat | $20.00 | Bourbon | Barton 1792 | Wheated | Banana, Tropical, Vanilla |
| Basil Hayden 10 yr | $24.00 | Bourbon | Jim Beam | High Rye | Peppermint, Spice, Oak |
| BT Kosher Wheat | $19.00 | Bourbon | Buffalo Trace | Wheated | Honey, Apple, Butterscotch |
| E.H. Taylor BP | $50.00 | Bourbon | Buffalo Trace | Mash Bill #1 | Blackberry, Caramel, Tobacco |
| George T. Stagg | $165.00 | Bourbon | Buffalo Trace | Mash Bill #1 | Molasses, Chocolate, Dates |
| Eagle Rare 17 yr | $225.00 | Bourbon | Buffalo Trace | Mash Bill #1 | Cherry, Leather, Almond |
| Double Eagle VR | $1,000.00 | Bourbon | Buffalo Trace | Mash Bill #1 | Toasted Oak, Vanilla, Rich Caramel |
