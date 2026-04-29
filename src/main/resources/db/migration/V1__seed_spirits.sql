-- =============================================================================
-- Russ AI — V1 Complete Spirit Inventory Seed
-- Source: Hangry Bison Spirit Menu (Regular + Allocation List)
-- Columns: spirit_id, name, category, distillery, mash_bill, flavor_tags,
--          price_pour, proof, age_statement, batch_type, finish
-- =============================================================================

-- =============================================================================
-- BOURBON — REGULAR MENU
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

-- Buffalo Trace — Mash Bill #1
(gen_random_uuid(), 'Buffalo Trace', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Vanilla, Caramel, Toffee, Brown Sugar, Hints of Citrus', 12.00, 90.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Eagle Rare Single Barrel', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Cherry, Oak, Toffee, Dark Chocolate, Leather', 18.00, 90.0, 10, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Benchmark Bonded', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Vanilla, Caramel, Light Oak, Grain', 10.00, 100.0, NULL, 'Bottled in Bond', 'None'),
(gen_random_uuid(), 'Benchmark Full Proof', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Bold Caramel, Oak, Dried Fruit, Baking Spice', 13.00, 125.0, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'Benchmark Single Barrel', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Vanilla, Toasted Oak, Mild Fruit, Nutmeg', 12.00, 90.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Benchmark Small Batch', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Sweet Corn, Light Vanilla, Apple, Mild Spice', 8.00, 80.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Benchmark Top Floor', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Rich Caramel, Toasted Grain, Brown Sugar, Warm Spice', 7.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Stagg Jr', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Dark Fruit, Cinnamon, Chocolate, Leather, Oak', 30.00, 130.0, NULL, 'Barrel Proof', 'None'),

-- Buffalo Trace — Mash Bill #2
(gen_random_uuid(), 'Blanton''s Single Barrel', 'Bourbon', 'Buffalo Trace', 'Mash Bill #2 — 75% Corn, 15% Rye, 10% Malted Barley', 'Citrus, Candied Orange, Caramel, Floral, Vanilla', 25.00, 93.0, NULL, 'Single Barrel', 'None'),

-- Buffalo Trace — Wheated
(gen_random_uuid(), 'Weller 12 yr', 'Bourbon', 'Buffalo Trace', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Honey, Caramel, Vanilla, Baked Apple, Butterscotch', 50.00, 90.0, 12, 'Small Batch', 'None'),

-- Barton 1792
(gen_random_uuid(), '1792', 'Bourbon', 'Barton 1792', 'High Rye — 74% Corn, 18% Rye, 8% Malted Barley', 'Caramel, Spice, Dried Fruit, Oak, Nutmeg', 18.00, 93.7, NULL, 'Small Batch', 'None'),

-- Brown-Forman / Woodford Reserve
(gen_random_uuid(), 'Woodford Reserve', 'Bourbon', 'Brown-Forman / Woodford Reserve', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Dried Fruit, Caramel, Vanilla, Chocolate, Toasted Oak', 12.00, 90.4, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Woodford Reserve Double Oaked', 'Bourbon', 'Brown-Forman / Woodford Reserve', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Rich Vanilla, Caramel, Dark Chocolate, Toasted Oak, Honey', 22.00, 90.4, NULL, 'Small Batch', 'Secondary Oak Barrel'),

-- Brown-Forman / Old Forester
(gen_random_uuid(), 'Old Forester', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Brown Sugar, Toasted Wood, Citrus, Spice', 7.00, 86.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Old Forester 1870', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Apple, Vanilla, Toasted Oak, Caramel, Baking Spice', 14.00, 90.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Old Forester 1897', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Caramel, Candied Ginger, Dried Cherry, Toasted Wood', 17.00, 100.0, NULL, 'Bottled in Bond', 'None'),
(gen_random_uuid(), 'Old Forester 1910', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Chocolate, Caramel, Dark Fruit, Toasted Grain', 18.00, 93.0, NULL, 'Small Batch', 'Secondary Oak Barrel'),
(gen_random_uuid(), 'Old Forester Prohibition', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Caramel, Anise, Clove, Toasted Rye, Dark Oak', 17.00, 95.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Old Forester Statesman', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Buttered Toffee, Rye Spice, Dark Fruit, Tobacco', 17.00, 95.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Old Grand Dad Bonded', 'Bourbon', 'Beam Suntory', 'High Rye — 63% Corn, 27% Rye, 10% Malted Barley', 'Spicy Rye, Cinnamon, Oak, Fruit, Pepper', 7.00, 100.0, NULL, 'Bottled in Bond', 'None'),

-- Beam Suntory
(gen_random_uuid(), 'Jim Beam Black', 'Bourbon', 'Beam Suntory', 'Standard Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Caramel, Vanilla, Oak, Dark Fruit, Toasted Grain', 8.00, 86.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Basil Hayden', 'Bourbon', 'Beam Suntory', 'High Rye — 63% Corn, 27% Rye, 10% Malted Barley', 'Peppery Rye, Citrus, Honey, Light Vanilla, Dried Herbs', 13.00, 80.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Baker''s', 'Bourbon', 'Beam Suntory', 'Standard Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Vanilla, Caramel, Cinnamon, Roasted Nuts, Dried Fruit', 18.00, 107.0, 7, 'Small Batch', 'None'),
(gen_random_uuid(), 'Booker''s', 'Bourbon', 'Beam Suntory', 'Standard Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Bold Oak, Dark Chocolate, Tobacco, Vanilla, Caramel', 26.00, 126.0, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'Knob Creek', 'Bourbon', 'Beam Suntory', 'Standard Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Rich Caramel, Oak, Vanilla, Toasted Nuts, Dried Fruit', 11.00, 100.0, 9, 'Small Batch', 'None'),
(gen_random_uuid(), 'Legent', 'Bourbon', 'Beam Suntory', 'Standard Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Fruit Forward, Plum, Caramel, Vanilla, Floral', 15.00, 94.0, NULL, 'Small Batch', 'Wine and Sherry Cask'),

-- Wild Turkey
(gen_random_uuid(), 'Wild Turkey 101', 'Bourbon', 'Wild Turkey Distillery', 'High Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Vanilla, Honey, Clove, Cinnamon, Orange Peel', 10.00, 101.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Wild Turkey Rare Breed', 'Bourbon', 'Wild Turkey Distillery', 'High Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Bold Vanilla, Caramel, Dark Fruit, Oak, Cinnamon', 19.00, 116.8, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'Russell''s Reserve', 'Bourbon', 'Wild Turkey Distillery', 'High Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Caramel, Vanilla, Dark Fruit, Toasted Oak, Light Spice', 15.00, 90.0, 10, 'Small Batch', 'None'),

-- Four Roses
(gen_random_uuid(), 'Four Roses Single Barrel', 'Bourbon', 'Four Roses Distillery', 'High Rye — 60% Corn, 35% Rye, 5% Malted Barley', 'Fruit, Caramel, Cinnamon, Vanilla, Herbs', 15.00, 100.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Four Roses Small Batch', 'Bourbon', 'Four Roses Distillery', 'Blend — 60/75% Corn, 20/35% Rye, 5% Malted Barley', 'Caramel, Fruit, Light Spice, Floral, Vanilla', 11.00, 90.0, NULL, 'Small Batch', 'None'),

-- Maker's Mark
(gen_random_uuid(), 'Maker''s Mark', 'Bourbon', 'Maker''s Mark Distillery', 'Wheated — 70% Corn, 16% Red Winter Wheat, 14% Malted Barley', 'Caramel, Vanilla, Fruit, Soft Spice, Light Oak', 10.00, 90.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Maker''s 46', 'Bourbon', 'Maker''s Mark Distillery', 'Wheated — 70% Corn, 16% Red Winter Wheat, 14% Malted Barley', 'Caramel, Vanilla, Toasted Oak, Cinnamon, Dried Fruit', 13.00, 94.0, NULL, 'Small Batch', 'French Oak Stave'),
(gen_random_uuid(), 'Maker''s Mark 101', 'Bourbon', 'Maker''s Mark Distillery', 'Wheated — 70% Corn, 16% Red Winter Wheat, 14% Malted Barley', 'Bolder Caramel, Vanilla, Baking Spice, Oak, Butterscotch', 15.00, 101.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Maker''s Mark Cask Strength', 'Bourbon', 'Maker''s Mark Distillery', 'Wheated — 70% Corn, 16% Red Winter Wheat, 14% Malted Barley', 'Intense Caramel, Vanilla, Dark Fruit, Rich Oak, Toffee', 16.00, 110.0, NULL, 'Cask Strength', 'None'),

-- Heaven Hill
(gen_random_uuid(), 'Elijah Craig Small Batch', 'Bourbon', 'Heaven Hill', 'Standard Rye — 78% Corn, 10% Rye, 12% Malted Barley', 'Vanilla, Oak, Caramel, Baking Spice, Toasted Nuts', 11.00, 94.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Elijah Craig Single Barrel Proof', 'Bourbon', 'Heaven Hill', 'Standard Rye — 78% Corn, 10% Rye, 12% Malted Barley', 'Bold Oak, Dark Fruit, Cinnamon, Chocolate, Rich Caramel', 22.00, 120.0, NULL, 'Single Barrel Barrel Proof', 'None'),
(gen_random_uuid(), 'Henry McKenna Bottled in Bond', 'Bourbon', 'Heaven Hill', 'Standard Rye — 78% Corn, 10% Rye, 12% Malted Barley', 'Caramel, Vanilla, Fruit, Oak, Mild Spice', 15.00, 100.0, 10, 'Bottled in Bond', 'None'),

-- E.H. Taylor
(gen_random_uuid(), 'E.H. Taylor Small Batch', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Caramel, Vanilla, Sweet Grain, Baking Spice, Light Floral', 15.00, 100.0, NULL, 'Bottled in Bond', 'None'),

-- Michter's
(gen_random_uuid(), 'Michter''s', 'Bourbon', 'Michter''s Distillery', 'Undisclosed — Est. 79% Corn, 11% Rye, 10% Malted Barley', 'Caramel, Dried Fruit, Vanilla, Toasted Oak, Light Spice', 17.00, 91.4, NULL, 'Small Batch', 'None'),

-- High West
(gen_random_uuid(), 'High West', 'Bourbon', 'High West Distillery', 'Sourced Blend — MGP Indiana + High West', 'Caramel, Vanilla, Mild Spice, Light Fruit', 12.00, 88.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'High West Campfire', 'Bourbon', 'High West Distillery', 'Blend — Bourbon + Rye + Scotch', 'Smoke, Caramel, Vanilla, Dried Fruit, Peat', 25.00, 88.0, NULL, 'Blended', 'None'),

-- Angel's Envy
(gen_random_uuid(), 'Angel''s Envy', 'Bourbon', 'Louisville Distilling Co.', 'Standard Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Vanilla, Maple, Candied Fruit, Light Spice, Toasted Oak', 15.00, 86.6, NULL, 'Small Batch', 'Port Wine Cask'),

-- Jefferson's
(gen_random_uuid(), 'Jefferson''s Ocean Aged at Sea', 'Bourbon', 'Jefferson''s / Various Sourced', 'Sourced — Various Mash Bills', 'Salted Caramel, Vanilla, Brine, Tropical Fruit, Toasted Oak', 23.00, 90.0, NULL, 'Blended', 'Ocean Aged'),

-- Rabbit Hole
(gen_random_uuid(), 'Rabbit Hole Dareringer', 'Bourbon', 'Rabbit Hole Distillery', 'High Rye — 70% Corn, 20% Rye, 10% Malted Barley', 'Dark Fruit, Caramel, Sherry, Toasted Oak, Vanilla', 29.00, 93.0, NULL, 'Small Batch', 'PX Sherry Cask'),

-- Widow Jane
(gen_random_uuid(), 'Widow Jane', 'Bourbon', 'Widow Jane Distillery', 'Sourced + Blended — Various', 'Vanilla, Caramel, Dried Fruit, Floral, Light Smoke', 22.00, 91.0, NULL, 'Blended', 'None'),

-- George Dickel
(gen_random_uuid(), 'George Dickel', 'Tennessee Whiskey', 'Cascade Hollow Distilling', 'Standard — 84% Corn, 8% Rye, 8% Malted Barley', 'Corn, Vanilla, Caramel, Toasted Grain, Mild Smoke', 10.00, 80.0, NULL, 'Standard', 'None'),

-- Redwood Empire
(gen_random_uuid(), 'Redwood Empire Pipe Dream', 'Bourbon', 'Redwood Empire Distilling', 'Sourced — Est. Wheated Mash', 'Honey, Caramel, Vanilla, Toasted Oak, Light Fruit', 10.00, 90.0, NULL, 'Blended', 'None'),

-- Bardstown
(gen_random_uuid(), 'Bardstown Straight', 'Bourbon', 'Bardstown Bourbon Company', 'Sourced High Rye — Est. 60% Corn, 36% Rye, 4% Malted Barley', 'Caramel, Rye Spice, Dark Fruit, Toasted Grain', 17.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Bardstown Wheated', 'Bourbon', 'Bardstown Bourbon Company', 'Sourced Wheated — Est. 61% Corn, 31% Wheat, 8% Malted Barley', 'Honey, Soft Vanilla, Apple, Caramel, Light Spice', 18.00, 90.0, NULL, 'Small Batch', 'None'),

-- Horse Soldier
(gen_random_uuid(), 'Horse Soldier', 'Bourbon', 'American Freedom Distillery', 'Standard — 73% Corn, 17% Wheat, 10% Malted Barley', 'Vanilla, Caramel, Dried Fruit, Toasted Wood, Mild Spice', 15.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Horse Soldier Barrel Strength', 'Bourbon', 'American Freedom Distillery', 'Standard — 73% Corn, 17% Wheat, 10% Malted Barley', 'Bold Caramel, Oak, Dark Fruit, Rich Vanilla, Toasted Grain', 24.00, 129.0, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'Horse Soldier Small Batch', 'Bourbon', 'American Freedom Distillery', 'Standard — 73% Corn, 17% Wheat, 10% Malted Barley', 'Honey, Vanilla, Apple, Mild Spice, Oak', 18.00, 90.0, NULL, 'Small Batch', 'None'),

-- 2XO
(gen_random_uuid(), '2XO American Oak', 'Bourbon', '2XO / Sourced Blend', 'Sourced — Various', 'Vanilla, Caramel, Toasted Oak, Grain, Light Fruit', 15.00, 86.0, NULL, 'Blended', 'American Oak'),
(gen_random_uuid(), '2XO French Oak', 'Bourbon', '2XO / Sourced Blend', 'Sourced — Various', 'Rich Fruit, Vanilla, Toasted Oak, Subtle Tannin', 15.00, 86.0, NULL, 'Blended', 'French Oak'),

-- Bowman Brothers
(gen_random_uuid(), 'Bowman Brothers Small Batch', 'Bourbon', 'A. Smith Bowman Distillery', 'Standard Rye — Est. 75% Corn, 15% Rye, 10% Malted Barley', 'Vanilla, Caramel, Fruit, Toasted Grain, Light Spice', 15.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Bowman Brothers Port Barrels', 'Bourbon', 'A. Smith Bowman Distillery', 'Standard Rye — Est. 75% Corn, 15% Rye, 10% Malted Barley', 'Dark Fruit, Tart Berry, Caramel, Vanilla, Toasted Oak', 20.00, 90.0, NULL, 'Small Batch', 'Port Wine Cask'),

-- Others
(gen_random_uuid(), 'Charles Woodson Signature', 'Bourbon', 'Sourced / Charles Woodson Spirits', 'Sourced — Est. High Rye', 'Caramel, Vanilla, Spice, Toasted Oak, Dark Fruit', 17.00, 88.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Chicken Cock', 'Bourbon', 'Bardstown Bourbon Company', 'High Rye — Sourced Est. 60% Corn, 36% Rye, 4% Malted Barley', 'Caramel, Rye Spice, Oak, Dried Cherry, Vanilla', 20.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Heaven''s Door Ascension KY Bourbon', 'Bourbon', 'Heaven''s Door Spirits', 'Sourced KY — Est. Standard Rye Mash', 'Caramel, Vanilla, Fruit, Light Spice, Toasted Oak', 16.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Heaven''s Door Revival TN Bourbon', 'Bourbon', 'Heaven''s Door Spirits', 'Tennessee Sourced — Est. Standard Mash', 'Sweet Corn, Vanilla, Toasted Wood, Mild Spice', 16.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Wyoming Whiskey Bourbon', 'Bourbon', 'Wyoming Whiskey', 'Standard — 68% Corn, 20% Wheat, 12% Malted Barley', 'Honey, Dried Fruit, Vanilla, Light Spice, Caramel', 10.00, 88.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Bulleit', 'Bourbon', 'Four Roses (for Diageo/Bulleit)', 'High Rye — 68% Corn, 28% Rye, 4% Malted Barley', 'Vanilla, Honey, Caramel, Rye Spice, Dried Fruit', 9.00, 90.0, NULL, 'Standard', 'None');


-- =============================================================================
-- BOURBON — ALLOCATION LIST (Premium / Limited / Rare)
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

(gen_random_uuid(), '1792 Sweet Wheat', 'Bourbon', 'Barton 1792', 'Wheated — Est. 74% Corn, 18% Wheat, 8% Malted Barley', 'Banana, Tropical, Vanilla, Light Spice', 20.00, 91.2, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), '2XO Gem of Kentucky', 'Bourbon', '2XO / Sourced Blend', 'Sourced KY — Single Barrel 16yr', 'Deep Oak, Dried Fruit, Rich Caramel, Vanilla, Leather', 96.00, 96.4, 16, 'Single Barrel', 'None'),
(gen_random_uuid(), '2XO Icon Series', 'Bourbon', '2XO / Sourced Blend', 'Sourced — Various Premium Barrels', 'Toasted Oak, Dark Caramel, Spice, Stone Fruit', 32.00, 92.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Angel''s Envy Cask Strength', 'Bourbon', 'Louisville Distilling Co.', 'Standard Rye — Port Wine Cask Finished, Uncut', 'Intense Vanilla, Maple, Dark Fruit, Port Wine, Bold Spice', 105.00, 119.4, NULL, 'Cask Strength', 'Port Wine Cask'),
(gen_random_uuid(), 'Bakers High Rye', 'Bourbon', 'Beam Suntory', 'High Rye — 63% Corn, 27% Rye, 10% Malted Barley', 'Peppery Rye, Citrus, Caramel, Vanilla, Dried Herbs', 25.00, 107.0, 7, 'Small Batch', 'None'),
(gen_random_uuid(), 'Basil Hayden 10 yr', 'Bourbon', 'Beam Suntory', 'High Rye — 63% Corn, 27% Rye, 10% Malted Barley', 'Peppermint, Spice, Oak, Dried Citrus, Caramel', 24.00, 80.0, 10, 'Small Batch', 'None'),
(gen_random_uuid(), 'Blanton''s Straight From the Barrel', 'Bourbon', 'Buffalo Trace', 'Mash Bill #2 — 75% Corn, 15% Rye, 10% Malted Barley', 'Bold Candied Orange, Caramel, Vanilla, Rich Spice, Full Oak', 50.00, 128.6, NULL, 'Single Barrel Barrel Proof', 'None'),
(gen_random_uuid(), 'Blanton''s Gold Edition', 'Bourbon', 'Buffalo Trace', 'Mash Bill #2 — 75% Corn, 15% Rye, 10% Malted Barley', 'Citrus, Honey, Caramel, Toasted Oak, Floral', 50.00, 103.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Bombergers', 'Bourbon', 'Michter''s Distillery (Heritage Label)', 'Undisclosed — Est. Pennsylvania Style Mash', 'Bold Oak, Vanilla, Dark Fruit, Spice, Caramel', 36.00, 108.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Buffalo Trace Kosher Wheat', 'Bourbon', 'Buffalo Trace', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Honey, Apple, Butterscotch, Light Vanilla, Baking Spice', 19.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Buffalo Trace Kosher Rye', 'Bourbon', 'Buffalo Trace', 'High Rye — 75% Corn, 15% Rye, 10% Malted Barley', 'Spice, Dried Fruit, Caramel, Toasted Grain, Rye Pepper', 19.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Buffalo Trace Kosher Straight Rye', 'Rye Whiskey', 'Buffalo Trace', 'Rye Mash — 51%+ Rye Grain', 'Bold Rye, Pepper, Citrus, Caramel, Dried Herb', 19.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Caribou Crossing', 'Bourbon', 'Sazerac / Canadian Source', 'Wheated — Undisclosed Canadian Mash', 'Honey, Vanilla, Caramel, Apple, Soft Spice', 50.00, 80.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Charter Oak Spanish Oak (WG)', 'Bourbon', 'Brown-Forman / Charter Oak', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Dark Fruit, Toasted Wood, Tannin, Caramel, Vanilla', 70.00, 90.0, NULL, 'Single Barrel', 'Spanish Oak'),
(gen_random_uuid(), 'Cognac Park', 'Cognac', 'Park Distillery / Cognac, France', 'Ugni Blanc Grape-Based', 'Dried Apricot, Vanilla, Honey, Floral, Light Oak', 26.00, 80.0, NULL, 'Standard', 'Limousin Oak'),
(gen_random_uuid(), 'E.H. Taylor Barrel Proof', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Blackberry, Caramel, Tobacco, Bold Spice, Rich Vanilla', 50.00, 140.6, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'E.H. Taylor Barrel Proof Rye', 'Rye Whiskey', 'Buffalo Trace', 'Rye Mash — 51%+ Rye', 'Bold Rye Spice, Citrus, Dark Fruit, Toasted Oak, Pepper', 50.00, 138.0, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'E.H. Taylor Single Barrel', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Vanilla, Caramel, Honey, Light Smoke, Dried Fruit', 30.00, 100.0, NULL, 'Single Barrel Bottled in Bond', 'None'),
(gen_random_uuid(), 'E.H. Taylor Rye', 'Rye Whiskey', 'Buffalo Trace', 'Rye Mash — 51%+ Rye', 'Rye Spice, Pepper, Citrus, Caramel, Herbal', 40.00, 100.0, NULL, 'Bottled in Bond', 'None'),
(gen_random_uuid(), 'Elijah Craig 18 yr', 'Bourbon', 'Heaven Hill', 'Standard Rye — 78% Corn, 10% Rye, 12% Malted Barley', 'Leather, Dark Oak, Dried Cherry, Caramel, Tobacco', 39.00, 90.0, 18, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Elijah Craig Toasted Rye', 'Bourbon', 'Heaven Hill', 'Standard Rye — 78% Corn, 10% Rye, 12% Malted Barley', 'Sweet Rye, Caramel, Toasted Grain, Vanilla, Spice', 15.00, 94.0, NULL, 'Small Batch', 'Toasted New Oak'),
(gen_random_uuid(), 'Elmer T. Lee', 'Bourbon', 'Buffalo Trace', 'Mash Bill #2 — 75% Corn, 15% Rye, 10% Malted Barley', 'Citrus, Honey, Floral, Light Caramel, Toasted Oak', 25.00, 90.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'George T. Stagg', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Molasses, Chocolate, Dates, Dark Fruit, Bold Oak, Leather', 165.00, 142.5, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'Heaven Hill GTG Bourbon', 'Bourbon', 'Heaven Hill', 'Standard Rye — 78% Corn, 10% Rye, 12% Malted Barley', 'Caramel, Vanilla, Toasted Grain, Oak, Dried Fruit', 32.00, 100.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Heaven Hill GTG Rye', 'Rye Whiskey', 'Heaven Hill', 'Rye Mash — 51%+ Rye', 'Rye Spice, Pepper, Vanilla, Dried Herb, Fruit', 32.00, 100.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Heaven Hill GTG Wheated', 'Bourbon', 'Heaven Hill', 'Wheated — Est. 70%+ Corn, 16% Wheat, 14% Malted Barley', 'Honey, Caramel, Soft Spice, Vanilla, Apple', 32.00, 100.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Heaven Hill Bottled in Bond', 'Bourbon', 'Heaven Hill', 'Standard Rye — 78% Corn, 10% Rye, 12% Malted Barley', 'Caramel, Vanilla, Dried Fruit, Corn Sweetness, Mild Spice', 15.00, 100.0, 7, 'Bottled in Bond', 'None'),
(gen_random_uuid(), 'Heavens Door Bootleg', 'Bourbon', 'Heaven''s Door Spirits', 'Sourced — Various, Uncut', 'Bold Oak, Dark Caramel, Dried Fruit, Rich Vanilla, Tobacco', 150.00, 125.0, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'Heavens Door 10 yr', 'Bourbon', 'Heaven''s Door Spirits', 'Sourced — Various', 'Caramel, Vanilla, Toasted Oak, Dried Fruit, Mild Spice', 30.00, 90.0, 10, 'Small Batch', 'None'),
(gen_random_uuid(), 'High West A Midwinter Night''s Dram', 'Rye Whiskey', 'High West Distillery', 'Rye — Sourced + Finished in Port and French Oak', 'Dark Fruit, Port Wine, Vanilla, Spice, Toasted Oak', 52.00, 98.6, NULL, 'Small Batch', 'Port and French Oak'),
(gen_random_uuid(), 'High West Bourye', 'Whiskey', 'High West Distillery', 'Blend — Bourbon and Rye Mash Bills', 'Sweet Caramel, Rye Spice, Fruit, Vanilla, Oak', 35.00, 92.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'High West High Country', 'American Single Malt', 'High West Distillery', 'Single Malt — 100% Malted Barley', 'Honey, Floral, Light Fruit, Toasted Grain, Mild Oak', 30.00, 88.0, NULL, 'Single Malt', 'None'),
(gen_random_uuid(), 'High West Prisoner''s Share', 'Bourbon', 'High West Distillery', 'Sourced — Aged in Cognac and Armagnac Barrels', 'Dried Fruit, Cognac, Caramel, Vanilla, Toasted Oak', 50.00, 92.0, NULL, 'Small Batch', 'Cognac and Armagnac Cask'),
(gen_random_uuid(), 'Jack Daniels Coy Hill', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Bold Vanilla, Caramel, Toasted Grain, Rich Oak, Brown Sugar', 60.00, 142.5, NULL, 'Single Barrel Barrel Proof', 'None'),
(gen_random_uuid(), 'Jack Daniels Twice Barreled', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Rich Caramel, Toasted Oak, Vanilla, Dark Fruit, Spice', 50.00, 86.0, NULL, 'Small Batch', 'Secondary Oak Barrel'),
(gen_random_uuid(), 'Jack Daniels Special Reserve', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Caramel, Vanilla, Light Oak, Grain, Mild Spice', 50.00, 86.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Jack Daniels 10 yr', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Deep Oak, Vanilla, Caramel, Dried Fruit, Toasted Grain', 30.00, 86.0, 10, 'Small Batch', 'None'),
(gen_random_uuid(), 'Jack Daniels 12 yr', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Rich Oak, Caramel, Dark Fruit, Leather, Tobacco', 60.00, 86.0, 12, 'Small Batch', 'None'),
(gen_random_uuid(), 'Jack Daniels 14 yr', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Deep Leather, Dark Dried Fruit, Bold Oak, Tobacco, Rich Vanilla', 75.00, 86.0, 14, 'Small Batch', 'None'),
(gen_random_uuid(), 'Jack Daniels Sinatra', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Caramel, Charcoal, Toasted Oak, Vanilla, Brown Sugar', 25.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Jefferson''s Ocean Aged at Sea Rye', 'Rye Whiskey', 'Jefferson''s / Various Sourced', 'Rye Mash — Various Sourced', 'Brine, Spicy Rye, Tropical Fruit, Light Caramel, Sea Salt', 23.00, 90.0, NULL, 'Blended', 'Ocean Aged'),
(gen_random_uuid(), 'Kentucky Owl Batch', 'Bourbon', 'Bardstown Bourbon Co. / Various', 'Blended — Various KY Bourbon Mash Bills', 'Caramel, Dried Fruit, Baking Spice, Toasted Oak, Vanilla', 100.00, 96.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Knob Creek 18 yr', 'Bourbon', 'Beam Suntory', 'Standard Rye — 75% Corn, 13% Rye, 12% Malted Barley', 'Leather, Deep Oak, Dark Fruit, Tobacco, Rich Caramel', 80.00, 100.0, 18, 'Small Batch', 'None'),
(gen_random_uuid(), 'Little Book Chapter 7 (WG)', 'Whiskey', 'Beam Suntory', 'Blended — Bourbon + Rye + Scotch World Grain', 'Dried Fruit, Caramel, Rye Spice, Toasted Grain, Vanilla', 85.00, 116.2, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Makers Mark Cellar Aged', 'Bourbon', 'Maker''s Mark Distillery', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Caramel, Honey, Light Fruit, Soft Spice, Butterscotch', 55.00, 88.0, NULL, 'Small Batch', 'Cold Cellar Aged'),
(gen_random_uuid(), 'Makers Mark The Heart Release', 'Bourbon', 'Maker''s Mark Distillery', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Rich Vanilla, Caramel, Toasted Oak, Fruit, Deep Spice', 25.00, 110.0, NULL, 'Cask Strength', 'None'),
(gen_random_uuid(), 'Michters Toasted Mash', 'Bourbon', 'Michter''s Distillery', 'Undisclosed — Est. Toasted Grain Mash', 'Toasted Grain, Caramel, Vanilla, Nutmeg, Brown Sugar', 36.00, 91.4, NULL, 'Small Batch', 'Toasted Barrel'),
(gen_random_uuid(), 'Michters 10 yr', 'Bourbon', 'Michter''s Distillery', 'Undisclosed — Est. 79% Corn, 11% Rye, 10% Malted Barley', 'Dark Fruit, Leather, Caramel, Toasted Oak, Rich Vanilla', 55.00, 94.4, 10, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Michters 10 yr Rye', 'Rye Whiskey', 'Michter''s Distillery', 'Rye Mash — 51%+ Rye', 'Bold Rye Spice, Dried Herb, Citrus, Dark Fruit, Toasted Oak', 60.00, 92.8, 10, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Michter''s Toasted Rye', 'Rye Whiskey', 'Michter''s Distillery', 'Rye Mash — 51%+ Rye', 'Caramel Rye, Toasted Grain, Vanilla, Pepper, Nutmeg', 36.00, 91.4, NULL, 'Small Batch', 'Toasted Barrel'),
(gen_random_uuid(), 'Mister Sams (WG)', 'Whiskey', 'Sazerac Company', 'Blended — Bourbon + Canadian Rye World Grain', 'Rich Caramel, Vanilla, Rye Spice, Toasted Oak, Dried Fruit', 350.00, 90.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'O.F.C. (WG)', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Deep Oak, Rich Caramel, Chocolate, Dried Fruit, Leather, Tobacco', 625.00, 100.0, NULL, 'Single Barrel Barrel Proof', 'None'),
(gen_random_uuid(), 'Old Fitzgerald 10 yr', 'Bourbon', 'Heaven Hill', 'Wheated — Est. 70% Corn, 16% Wheat, 14% Malted Barley', 'Honey, Caramel, Soft Vanilla, Apple, Butterscotch', 65.00, 100.0, 10, 'Bottled in Bond', 'None'),
(gen_random_uuid(), 'Old Forester 1924', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Rich Caramel, Oak, Toffee, Dark Fruit, Baking Spice', 37.00, 101.4, 10, 'Small Batch', 'None'),
(gen_random_uuid(), 'Old Forester Birthday Bourbon', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Dark Caramel, Toasted Oak, Dried Cherry, Leather, Rich Spice', 100.00, 100.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Old Forester President''s Choice', 'Bourbon', 'Brown-Forman', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Bold Oak, Dark Fruit, Caramel, Tobacco, Rich Vanilla', 70.00, 100.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Old Grand Dad 16 Yr', 'Bourbon', 'Beam Suntory', 'High Rye — 63% Corn, 27% Rye, 10% Malted Barley', 'Bold Rye Spice, Leather, Dark Dried Fruit, Oak, Tobacco', 62.00, 100.0, 16, 'Small Batch', 'None'),
(gen_random_uuid(), 'Old Rip Van Winkle 10 yr', 'Bourbon', 'Buffalo Trace', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Honey, Caramel, Vanilla, Apple, Butterscotch, Soft Oak', 125.00, 107.0, 10, 'Small Batch', 'None'),
(gen_random_uuid(), 'Van Winkle Special Reserve 12 yr', 'Bourbon', 'Buffalo Trace', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Rich Honey, Caramel, Dried Fruit, Toasted Oak, Vanilla', 165.00, 90.4, 12, 'Small Batch', 'None'),
(gen_random_uuid(), 'Van Winkle Family Reserve 15 yr', 'Bourbon', 'Buffalo Trace', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Deep Caramel, Leather, Dark Fruit, Rich Vanilla, Bold Oak', 250.00, 107.0, 15, 'Small Batch', 'None'),
(gen_random_uuid(), 'Van Winkle Family Reserve 20 yr', 'Bourbon', 'Buffalo Trace', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Leather, Deep Oak, Rich Dried Fruit, Tobacco, Dark Caramel', 325.00, 90.4, 20, 'Small Batch', 'None'),
(gen_random_uuid(), 'Van Winkle 23 yr', 'Bourbon', 'Buffalo Trace', 'Wheated — 70% Corn, 16% Wheat, 14% Malted Barley', 'Leather, Tobacco, Dark Fruit, Bold Oak, Deep Caramel', 450.00, 95.6, 23, 'Small Batch', 'None'),
(gen_random_uuid(), 'Van Winkle Family Reserve Rye 13 yr', 'Rye Whiskey', 'Buffalo Trace', 'Rye Mash — 51%+ Rye', 'Bold Rye Spice, Dried Herb, Leather, Citrus, Dark Fruit', 200.00, 95.4, 13, 'Small Batch', 'None'),
(gen_random_uuid(), 'Orphans Barrel Scotch Whisky', 'Scotch', 'Diageo / Various Distilleries', 'Malted Barley — Blended Scotch', 'Rich Dried Fruit, Toffee, Vanilla, Toasted Malt, Sherry', 98.00, 95.6, NULL, 'Blended', 'Sherry Cask'),
(gen_random_uuid(), 'Parker''s Heritage 10 yr', 'Bourbon', 'Heaven Hill', 'Standard Rye — 78% Corn, 10% Rye, 12% Malted Barley', 'Leather, Dark Caramel, Toasted Oak, Dark Fruit, Tobacco', 65.00, 110.0, 10, 'Small Batch', 'None'),
(gen_random_uuid(), 'Redwood Empire Cask Strength Emerald Giant', 'Bourbon', 'Redwood Empire Distilling', 'Sourced — Est. Wheated Mash', 'Bold Honey, Rich Caramel, Toasted Oak, Dark Fruit, Vanilla', 25.00, 116.0, NULL, 'Cask Strength', 'None'),
(gen_random_uuid(), 'Redwood Empire Cask Strength Lost Monarch', 'Bourbon', 'Redwood Empire Distilling', 'Sourced — Est. High Rye Mash', 'Bold Spice, Dark Caramel, Rich Oak, Pepper, Dried Fruit', 25.00, 116.0, NULL, 'Cask Strength', 'None'),
(gen_random_uuid(), 'Redwood Empire Cask Strength Pipe Dream', 'Bourbon', 'Redwood Empire Distilling', 'Sourced — Est. Wheated Mash', 'Honey, Caramel, Toasted Oak, Rich Vanilla, Dark Fruit', 25.00, 116.0, NULL, 'Cask Strength', 'None'),
(gen_random_uuid(), 'Sazerac 18 Yr', 'Rye Whiskey', 'Buffalo Trace', 'Rye Mash — 51%+ Rye', 'Deep Rye Spice, Leather, Citrus, Dark Dried Fruit, Tobacco', 125.00, 90.0, 18, 'Small Batch', 'None'),
(gen_random_uuid(), 'Thomas. H Handy', 'Rye Whiskey', 'Buffalo Trace', 'Rye Mash — 51%+ Rye', 'Bold Rye, Spice, Citrus, Caramel, Fresh Oak, Pepper', 125.00, 132.2, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'Daniel Weller Emmer Wheat (WP)', 'Bourbon', 'Buffalo Trace', 'Wheated — Emmer Wheat Heirloom Grain Experimental', 'Honey, Brioche, Toasted Grain, Caramel, Soft Floral', 350.00, 90.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Double Eagle Very Rare 20 yr', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Toasted Oak, Vanilla, Rich Caramel, Dried Fruit, Leather', 1000.00, 90.4, 20, 'Small Batch', 'None'),
(gen_random_uuid(), 'Eagle Rare 12 yr', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Cherry, Oak, Caramel, Dark Chocolate, Leather', 25.00, 90.0, 12, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Eagle Rare 17 yr', 'Bourbon', 'Buffalo Trace', 'Mash Bill #1 — 75% Corn, 10% Rye, 15% Malted Barley', 'Cherry, Leather, Almond, Deep Oak, Dried Fruit, Tobacco', 225.00, 101.0, 17, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Rare Hare', 'Bourbon', 'Rabbit Hole Distillery', 'High Rye — 70% Corn, 20% Rye, 10% Malted Barley', 'Dark Fruit, Caramel, Oak, Toasted Grain, Dried Cherry', 175.00, 100.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Rock Hills Farms', 'Bourbon', 'Buffalo Trace', 'Mash Bill #2 — 75% Corn, 15% Rye, 10% Malted Barley', 'Bold Citrus, Caramel, Honey, Toasted Oak, Spice', 68.00, 100.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Shenks', 'Bourbon', 'Limestone Branch Distillery', 'Wheated — Est. 70%+ Corn, 16% Wheat, 14% Malted Barley', 'Honey, Caramel, Soft Vanilla, Apple, Light Spice', 34.00, 91.2, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'WhistlePig The Boss Hog', 'Rye Whiskey', 'WhistlePig Farm', 'Rye Mash — 100% Rye Grain', 'Intense Rye Spice, Dark Fruit, Toasted Oak, Caramel, Leather', 165.00, 122.0, NULL, 'Single Barrel Barrel Proof', 'None'),
(gen_random_uuid(), 'Woodford Master''s Collection', 'Bourbon', 'Brown-Forman / Woodford Reserve', 'High Rye — 72% Corn, 18% Rye, 10% Malted Barley', 'Unique Finish, Rich Dried Fruit, Caramel, Toasted Oak, Vanilla', 50.00, 90.4, NULL, 'Small Batch', 'Special Annual Finish');


-- =============================================================================
-- WHISKEY RYE
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

(gen_random_uuid(), 'Angel''s Envy Rye', 'Rye Whiskey', 'Louisville Distilling Co.', 'Rye Mash — 95% Rye, 5% Malted Barley', 'Bold Rye Spice, Tropical Fruit, Vanilla, Molasses, Oak', 30.00, 100.0, NULL, 'Small Batch', 'Caribbean Rum Cask'),
(gen_random_uuid(), 'Basil Hayden Dark Rye', 'Rye Whiskey', 'Beam Suntory + Koval', 'Blend — Kentucky Rye + Canadian Rye', 'Peppery Rye, Plum, Chocolate, Mild Spice, Dried Fruit', 15.00, 80.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Bulleit Rye', 'Rye Whiskey', 'Four Roses (for Diageo)', 'High Rye — 95% Rye, 5% Malted Barley', 'Bold Rye, Pepper, Vanilla, Caramel, Dried Herb', 10.00, 90.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Elijah Craig Rye', 'Rye Whiskey', 'Heaven Hill', 'Rye Mash — 51%+ Rye', 'Rye Spice, Caramel, Vanilla, Dried Fruit, Light Oak', 13.00, 94.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Heaven''s Door Refuge', 'Rye Whiskey', 'Heaven''s Door Spirits', 'Rye Mash — Sourced KY Straight Rye', 'Rye Spice, Citrus, Toasted Oak, Vanilla, Exotic Wood', 21.00, 90.0, NULL, 'Small Batch', 'Mizunara Oak'),
(gen_random_uuid(), 'High West Rendezvous Rye', 'Rye Whiskey', 'High West Distillery', 'Blend — 95% Rye + 80% Rye Mash Bills', 'Bold Rye, Dried Fruit, Caramel, Vanilla, Baking Spice', 25.00, 92.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'High West Double Rye', 'Rye Whiskey', 'High West Distillery', 'Blend — 95% Rye + 53% Rye Mash Bills', 'Rye Spice, Caramel, Dried Herb, Fruit, Light Spice', 12.00, 92.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Kentucky Owl 10 Year Batch 4', 'Rye Whiskey', 'Bardstown Bourbon Co. / Various', 'Blended Rye — Various KY Rye Mash Bills', 'Deep Rye Spice, Caramel, Dark Fruit, Toasted Oak, Leather', 100.00, 96.0, 10, 'Blended', 'None'),
(gen_random_uuid(), 'Knob Creek Small Batch Rye', 'Rye Whiskey', 'Beam Suntory', 'Rye Mash — 51%+ Rye', 'Bold Rye, Caramel, Vanilla, Dried Herb, Toasted Grain', 12.00, 100.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Michter''s Rye', 'Rye Whiskey', 'Michter''s Distillery', 'Rye Mash — 51%+ Rye', 'Rye Spice, Caramel, Vanilla, Fruit, Toasted Oak', 18.00, 84.8, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Redwood Empire Emerald Giant', 'Rye Whiskey', 'Redwood Empire Distilling', 'Rye Mash — 51%+ Rye', 'Rye Spice, Citrus, Caramel, Oak, Dried Herb', 12.00, 90.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Sagamore Rye', 'Rye Whiskey', 'Sagamore Spirit Distillery', 'High Rye — 52% Rye, 43% Corn, 5% Malted Barley', 'Bold Rye, Citrus, Vanilla, Caramel, Baking Spice', 12.00, 83.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Sazerac Rye', 'Rye Whiskey', 'Buffalo Trace', 'Rye Mash — 51%+ Rye', 'Rye Spice, Fruit, Caramel, Anise, Toasted Oak', 10.00, 90.0, 6, 'Small Batch', 'None'),
(gen_random_uuid(), 'Widow Jane Applewood Rye', 'Rye Whiskey', 'Widow Jane Distillery', 'Rye Mash — Sourced', 'Smoke, Rye Spice, Caramel, Apple, Vanilla', 18.00, 92.0, NULL, 'Small Batch', 'Applewood Smoke'),
(gen_random_uuid(), 'WhistlePig 10 Year Straight Rye', 'Rye Whiskey', 'WhistlePig Farm / Alberta Distillers', 'Rye Mash — 100% Rye', 'Bold Rye Spice, Honey, Vanilla, Caramel, Toasted Oak', 26.00, 100.0, 10, 'Standard', 'None'),
(gen_random_uuid(), 'WhistlePig 12 Year Old World Rye', 'Rye Whiskey', 'WhistlePig Farm / Alberta Distillers', 'Rye Mash — 100% Rye', 'Rye Spice, Dark Fruit, Toffee, Toasted Oak, Sherry', 35.00, 86.4, 12, 'Small Batch', 'Old World Cask'),
(gen_random_uuid(), 'WhistlePig 15 Year Rye', 'Rye Whiskey', 'WhistlePig Farm / Alberta Distillers', 'Rye Mash — 100% Rye', 'Deep Rye Spice, Caramel, Dried Fruit, Leather, Oak', 95.00, 92.0, 15, 'Small Batch', 'None'),
(gen_random_uuid(), 'WhistlePig Farmstock', 'Rye Whiskey', 'WhistlePig Farm', 'Rye Mash — Vermont Estate Rye + Sourced', 'Rye Spice, Fresh Grain, Caramel, Apple, Light Oak', 22.00, 86.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'WhistlePig Piggyback', 'Rye Whiskey', 'WhistlePig Farm / Alberta Distillers', 'Rye Mash — 100% Rye', 'Rye Spice, Caramel, Dried Herb, Light Vanilla, Fruit', 19.00, 96.6, 6, 'Standard', 'None'),
(gen_random_uuid(), 'Woodford Reserve Rye', 'Rye Whiskey', 'Brown-Forman / Woodford Reserve', 'Rye Mash — 53% Rye, 33% Corn, 14% Malted Barley', 'Rye Spice, Floral, Dried Fruit, Caramel, Light Oak', 13.00, 90.4, NULL, 'Small Batch', 'None');


-- =============================================================================
-- SCOTCH & JAPANESE WHISKY
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

(gen_random_uuid(), 'The Balvenie 12 yr', 'Scotch', 'The Balvenie Distillery', '100% Malted Barley — Speyside', 'Honey, Vanilla, Fruit, Toasted Malt, Sherry', 22.00, 86.0, 12, 'Small Batch', 'Sherry Cask'),
(gen_random_uuid(), 'Dewars', 'Scotch', 'Dewar''s / John Dewar & Sons', '100% Malted Barley — Blended Scotch', 'Honey, Apple, Light Smoke, Vanilla, Grain', 9.00, 80.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Glenfiddich 14 yr Barrel Aged', 'Scotch', 'Glenfiddich Distillery', '100% Malted Barley — Speyside', 'Toffee, Vanilla, Apple, Toasted Oak, Light Honey', 16.00, 86.0, 14, 'Single Malt', 'Bourbon Cask'),
(gen_random_uuid(), 'Johnnie Walker Black Label', 'Scotch', 'Diageo', '100% Malted Barley — Blended 12yr', 'Smoky, Dark Fruit, Vanilla, Toasted Malt, Leather', 14.00, 80.0, 12, 'Blended', 'None'),
(gen_random_uuid(), 'Laphroaig 10 yr Single Malt', 'Scotch', 'Laphroaig Distillery', '100% Malted Barley — Islay, Heavily Peated', 'Intense Peat, Iodine, Sea Salt, Smoke, Dried Fruit', 17.00, 86.0, 10, 'Single Malt', 'None'),
(gen_random_uuid(), 'The Glenlivet 12', 'Scotch', 'The Glenlivet Distillery', '100% Malted Barley — Speyside', 'Citrus, Honey, Vanilla, Floral, Toasted Malt', 19.00, 80.0, 12, 'Single Malt', 'None'),
(gen_random_uuid(), 'The Macallan 12', 'Scotch', 'The Macallan Distillery', '100% Malted Barley — Speyside', 'Dried Fruit, Sherry, Vanilla, Toffee, Subtle Smoke', 25.00, 86.0, 12, 'Single Malt', 'Sherry Oak'),
(gen_random_uuid(), 'The Yamazaki 12 yr', 'Japanese Whisky', 'Suntory', '100% Malted Barley — Japanese Single Malt', 'Stone Fruit, Peach, Vanilla, Toasted Oak, Light Smoke', 65.00, 86.0, 12, 'Single Malt', 'Multiple Cask'),
(gen_random_uuid(), 'The Yamazaki 18 yr', 'Japanese Whisky', 'Suntory', '100% Malted Barley — Japanese Single Malt', 'Rich Dried Fruit, Sherry, Plum, Toasted Oak, Elegant Smoke', 230.00, 86.0, 18, 'Single Malt', 'Multiple Cask'),
(gen_random_uuid(), 'Hibiki Harmony', 'Japanese Whisky', 'Suntory', 'Blended — Malt + Grain Japanese Whisky', 'Honey, Citrus, Rose, Toasted Oak, White Peach, Subtle Smoke', 27.00, 86.0, NULL, 'Blended', 'Multiple Cask');


-- =============================================================================
-- TEQUILA & MEZCAL
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

(gen_random_uuid(), 'Clase Azul Plata', 'Tequila', 'Clase Azul Spirits', 'Blue Weber Agave — Blanco', 'Agave, Citrus, Floral, Vanilla, Mild Pepper', 45.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Clase Azul Reposado', 'Tequila', 'Clase Azul Spirits', 'Blue Weber Agave — Reposado', 'Agave, Vanilla, Caramel, Light Oak, Honey', 50.00, 80.0, NULL, 'Standard', 'American Oak'),
(gen_random_uuid(), 'Corazon Blanco', 'Tequila', 'Casa San Matías', 'Blue Weber Agave — Blanco', 'Fresh Agave, Citrus, Pepper, Light Floral', 8.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Divino Maguey Mezcal', 'Mezcal', 'Vago / Various Artisan', 'Espadin Agave — Artisan', 'Smoke, Agave, Citrus, Earth, Dried Fruit', 15.00, 90.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Dobel Maestro Extra Anejo', 'Tequila', 'Casa Maestri', 'Blue Weber Agave — Extra Anejo', 'Rich Caramel, Vanilla, Oak, Agave, Dried Fruit', 50.00, 80.0, NULL, 'Standard', 'American Oak'),
(gen_random_uuid(), 'Don Julio 1942', 'Tequila', 'Don Julio (Diageo)', 'Blue Weber Agave — Anejo', 'Vanilla, Caramel, Toasted Oak, Honey, Citrus', 60.00, 80.0, NULL, 'Small Batch', 'American Oak'),
(gen_random_uuid(), 'Don Julio Anejo', 'Tequila', 'Don Julio (Diageo)', 'Blue Weber Agave — Anejo', 'Agave, Vanilla, Caramel, Light Oak, Mild Spice', 13.00, 80.0, NULL, 'Standard', 'American Oak'),
(gen_random_uuid(), 'Ghost Blanco Tequila', 'Tequila', 'Ghost Tequila', 'Blue Weber Agave — Blanco', 'Agave, Citrus, Jalapeño Spice, Light Floral', 10.00, 70.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Herradura Anejo', 'Tequila', 'Casa Herradura', 'Blue Weber Agave — Anejo', 'Vanilla, Caramel, Toasted Wood, Agave, Honey', 20.00, 80.0, NULL, 'Standard', 'American Oak'),
(gen_random_uuid(), 'Herradura Reposado', 'Tequila', 'Casa Herradura', 'Blue Weber Agave — Reposado', 'Agave, Honey, Vanilla, Light Spice, Citrus', 10.00, 80.0, NULL, 'Standard', 'American Oak'),
(gen_random_uuid(), 'Patron', 'Tequila', 'Patrón Spirits', 'Blue Weber Agave — Reposado', 'Agave, Citrus, Honey, Mild Oak, Vanilla', 17.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Siete Misterios', 'Mezcal', 'Siete Misterios', 'Espadin Agave — Artisan', 'Smoke, Agave, Dried Herb, Citrus, Earth', 15.00, 92.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Tequila Ocho Blanco', 'Tequila', 'Tequila Ocho', 'Blue Weber Agave — Blanco Single Estate', 'Fresh Agave, Citrus, Pepper, Floral, Mineral', 15.00, 80.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Tequila Ocho Reposado', 'Tequila', 'Tequila Ocho', 'Blue Weber Agave — Reposado Single Estate', 'Agave, Honey, Vanilla, Light Spice, Citrus', 21.00, 80.0, NULL, 'Single Barrel', 'American Oak'),
(gen_random_uuid(), 'The Producer', 'Tequila', 'The Producer Tequila', 'Blue Weber Agave — Blanco', 'Fresh Agave, Citrus, Light Pepper, Herbal', 9.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Corazón de Agave Expresiones Anejo', 'Tequila', 'Casa San Matías', 'Blue Weber Agave — Anejo', 'Agave, Vanilla, Caramel, Oak, Butterscotch', 62.00, 80.0, NULL, 'Small Batch', 'Buffalo Trace Barrel');


-- =============================================================================
-- AMERICAN / TENNESSEE / CANADIAN / IRISH / OTHER WHISKEY
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

(gen_random_uuid(), 'Canadian Club', 'Canadian Whisky', 'Hiram Walker & Sons', 'Blended — Corn + Rye + Barley + Wheat', 'Light Grain, Vanilla, Mild Rye Spice, Caramel', 7.00, 80.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Crown Royal', 'Canadian Whisky', 'Crown Royal (Diageo)', 'Blended — 50 Grain Whiskies', 'Vanilla, Light Caramel, Dried Fruit, Grain, Mild Spice', 10.00, 80.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Firefly White Lightning', 'American Whiskey', 'Firefly Distillery', 'Corn Whiskey — Unaged', 'Corn, Light Grain, Sweet, Floral', 10.00, 100.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Gentleman Jack', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Smooth Vanilla, Caramel, Light Grain, Mild Oak', 11.00, 80.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Hooten Young 8 yr', 'American Whiskey', 'Hooten Young', 'Sourced — Est. Standard Mash', 'Caramel, Vanilla, Oak, Dried Fruit, Grain', 18.00, 86.0, 8, 'Small Batch', 'None'),
(gen_random_uuid(), 'Jack Daniel''s', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Vanilla, Caramel, Grain, Mild Oak, Light Fruit', 8.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Jack Daniel''s Barrel Proof', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Bold Vanilla, Caramel, Oak, Dark Fruit, Rich Grain', 21.00, 125.5, NULL, 'Barrel Proof', 'None'),
(gen_random_uuid(), 'Jack Daniel''s Bonded', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Caramel, Grain, Vanilla, Mild Oak, Light Spice', 13.00, 100.0, NULL, 'Bottled in Bond', 'None'),
(gen_random_uuid(), 'Jack Daniel''s Single Barrel', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Standard — 80% Corn, 8% Rye, 12% Malted Barley', 'Toasted Oak, Vanilla, Caramel, Dark Fruit, Grain', 14.00, 94.0, NULL, 'Single Barrel', 'None'),
(gen_random_uuid(), 'Jack Daniel''s Triple Mash', 'Tennessee Whiskey', 'Jack Daniel''s Distillery', 'Triple Grain — Corn + Rye + Barley', 'Soft Grain, Vanilla, Caramel, Mild Spice', 13.00, 86.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Jameson', 'Irish Whiskey', 'Midleton Distillery', 'Blended — Pot Still + Grain Irish Whiskey', 'Light Grain, Vanilla, Orchard Fruit, Mild Spice, Gentle Oak', 10.00, 80.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Michter''s US1 Sour Mash', 'American Whiskey', 'Michter''s Distillery', 'Sour Mash — Non-Standard Category', 'Caramel, Vanilla, Grain, Baking Spice, Light Oak', 18.00, 83.4, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Nelson''s Green Brier Sour Mash', 'Tennessee Whiskey', 'Nelson''s Green Brier Distillery', 'Sour Mash — 100% Tennessee Grain', 'Caramel, Dried Fruit, Vanilla, Toasted Grain, Mild Spice', 12.00, 91.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Ram''s Point Peanut Butter Whiskey', 'Flavored Whiskey', 'Ram''s Point Distillery', 'Flavored — Peanut Butter Infused', 'Peanut Butter, Vanilla, Caramel, Sweet Grain', 8.00, 60.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Redwood Empire Lost Monarch', 'Bourbon', 'Redwood Empire Distilling', 'Sourced Blend — Various', 'Honey, Caramel, Toasted Oak, Vanilla, Light Spice', 13.00, 90.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Stranahan''s Single Malt', 'American Single Malt', 'Stranahan''s Distillery', '100% Malted Barley — Colorado', 'Honey, Dried Fruit, Vanilla, Toasted Malt, Light Spice', 10.00, 94.0, NULL, 'Single Malt', 'None'),
(gen_random_uuid(), 'Suntory Toki', 'Japanese Whisky', 'Suntory', 'Blended — Malt + Grain Japanese Whisky', 'Honey, Light Citrus, Vanilla, Grain, Subtle Oak', 15.00, 86.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Uncle Nearest 1856', 'Tennessee Whiskey', 'Uncle Nearest Premium Whiskey', 'Sour Mash — Tennessee Grain Blend', 'Caramel, Vanilla, Dried Fruit, Toasted Oak, Mild Spice', 16.00, 100.0, NULL, 'Small Batch', 'None');


-- =============================================================================
-- VODKA
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

(gen_random_uuid(), 'Rain Organics Cucumber', 'Vodka', 'Rain Spirits', 'Organic Grain — Cucumber Infused', 'Cucumber, Fresh, Light Grain, Mild Citrus', 8.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Grey Goose', 'Vodka', 'Grey Goose Distillery', 'French Winter Wheat — Picardy Region', 'Clean, Subtle Citrus, Creamy, Light Almond', 13.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Ketel One', 'Vodka', 'Nolet Distillery', 'Wheat — Dutch', 'Clean, Citrus, Crisp Grain, Soft', 11.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Tito''s', 'Vodka', 'Fifth Generation Inc.', 'Corn — Texas', 'Clean Corn, Mild Sweet, Smooth, Neutral', 10.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Wheatley', 'Vodka', 'Buffalo Trace', 'Wheat — KY (10x Distilled)', 'Clean, Light Grain, Smooth, Very Neutral', 8.00, 80.0, NULL, 'Standard', 'None');


-- =============================================================================
-- GIN
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

(gen_random_uuid(), 'Barr Hill Gin', 'Gin', 'Caledonia Spirits', 'Corn + Raw Honey — Vermont', 'Honey, Juniper, Floral, Light Citrus, Herbal', 12.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Bombay Sapphire', 'Gin', 'Bacardi Limited', 'Grain — 10 Botanicals London Dry', 'Juniper, Citrus, Floral, Coriander, Angelica', 10.00, 94.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Hendrick''s', 'Gin', 'William Grant & Sons', 'Grain — Cucumber + Rose Petal Infused', 'Cucumber, Rose, Juniper, Citrus, Floral', 13.00, 88.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Tanqueray', 'Gin', 'Diageo', 'Grain — Classic London Dry', 'Juniper, Citrus, Coriander, Angelica, Piney', 9.00, 94.6, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Tinkerman''s', 'Gin', 'Tinkerman''s Distillery', 'Grain — Craft Artisan', 'Juniper, Citrus, Floral, Herbal, Mild Spice', 8.00, 80.0, NULL, 'Small Batch', 'None');


-- =============================================================================
-- RUM
-- =============================================================================
INSERT INTO spirits (spirit_id, name, category, distillery, mash_bill, flavor_tags, price_pour, proof, age_statement, batch_type, finish) VALUES

(gen_random_uuid(), '1888 Rum', 'Rum', '1888 Rum', 'Sugarcane — Aged', 'Caramel, Vanilla, Tropical Fruit, Light Oak', 10.00, 80.0, NULL, 'Small Batch', 'None'),
(gen_random_uuid(), 'Appleton', 'Rum', 'Appleton Estate', 'Sugarcane — Jamaican Blend', 'Tropical Fruit, Caramel, Oak, Vanilla, Spice', 9.00, 80.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Captain Morgan', 'Rum', 'Diageo', 'Sugarcane — Spiced Rum', 'Spice, Vanilla, Caramel, Tropical Fruit, Mild Oak', 8.00, 70.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Flor de Cana 12 yr', 'Rum', 'Compañía Licorera de Nicaragua', 'Sugarcane — Nicaraguan', 'Toffee, Vanilla, Dried Fruit, Toasted Oak, Light Spice', 14.00, 80.0, 12, 'Small Batch', 'None'),
(gen_random_uuid(), 'Flor de Cana 18 yr', 'Rum', 'Compañía Licorera de Nicaragua', 'Sugarcane — Nicaraguan', 'Rich Caramel, Dark Fruit, Toasted Oak, Vanilla, Molasses', 18.00, 80.0, 18, 'Small Batch', 'None'),
(gen_random_uuid(), 'Myers''s Dark', 'Rum', 'Myers''s Rum', 'Sugarcane — Jamaican Dark', 'Molasses, Dark Fruit, Caramel, Vanilla, Bold Spice', 8.00, 80.0, NULL, 'Blended', 'None'),
(gen_random_uuid(), 'Myers''s Platinum White', 'Rum', 'Myers''s Rum', 'Sugarcane — White', 'Sugarcane, Light Fruit, Clean, Mild Vanilla', 8.00, 80.0, NULL, 'Standard', 'None'),
(gen_random_uuid(), 'Ron Abuelo 12 yr', 'Rum', 'Varela Hermanos', 'Sugarcane — Panamanian', 'Caramel, Vanilla, Tropical Fruit, Toasted Oak, Honey', 20.00, 80.0, 12, 'Small Batch', 'None'),
(gen_random_uuid(), 'Rum Haven Coconut', 'Rum', 'Rum Haven', 'Sugarcane — Coconut Infused', 'Coconut, Vanilla, Tropical Fruit, Sweet, Light', 8.00, 42.0, NULL, 'Standard', 'None');

-- =============================================================================
-- END — 227 spirits seeded across all categories
-- Next migration: V2__add_vector_embeddings.sql
-- =============================================================================