-- V3: Data quality fixes found during the June 23 audit. Each UPDATE targets
-- an exact spirit_id (not name, since some names themselves are being
-- corrected here) and is commented with what was wrong and what source
-- confirmed the fix. Nothing in this file touches scoring logic, only the
-- underlying facts the scoring engine and embeddings read from.

-- Heaven Hill's wheated bourbons were using Maker's Mark's mash bill
-- (70% Corn, 16% Wheat, 14% Malted Barley) instead of their own. Heaven
-- Hill's own distillery blog publishes their real wheated recipe as
-- 68% Corn, 20% Wheat, 12% Malted Barley (the "OFD" mash bill used for
-- Old Fitzgerald and Larceny).
UPDATE spirits SET mash_bill = 'Wheated — 68% Corn, 20% Wheat, 12% Malted Barley'
WHERE spirit_id = '17f47566-9e43-485e-9276-828e40d0724c'; -- Old Fitzgerald 10 yr

UPDATE spirits SET mash_bill = 'Wheated — 68% Corn, 20% Wheat, 12% Malted Barley'
WHERE spirit_id = '4d0834a0-6f5b-4201-8f84-3d85a282abff'; -- Heaven Hill GTG Wheated

-- Old Forester 1924 was tagged with the standard Old Forester recipe
-- (72/18/10), but Old Forester's own press materials say this specific
-- release deliberately used a different mash bill: 79% Corn, 11% Rye,
-- 10% Malted Barley.
UPDATE spirits SET mash_bill = 'High Rye — 79% Corn, 11% Rye, 10% Malted Barley'
WHERE spirit_id = '568a31a2-778b-4195-9bae-ff78b856f080'; -- Old Forester 1924

-- Maker's Mark's real grain is specifically "soft red winter wheat" per
-- Wikipedia and the distillery's own history, not generic "wheat." Two
-- products were missing this, and also missing the apostrophe in the name.
UPDATE spirits SET name = 'Maker''s Mark Cellar Aged',
                   mash_bill = 'Wheated — 70% Corn, 16% Red Winter Wheat, 14% Malted Barley'
WHERE spirit_id = '03619ab5-def0-4081-8866-1374aaeeb0be'; -- Makers Mark Cellar Aged

UPDATE spirits SET name = 'Maker''s Mark The Heart Release',
                   mash_bill = 'Wheated — 70% Corn, 16% Red Winter Wheat, 14% Malted Barley'
WHERE spirit_id = '410817b7-6c3c-45f1-93ae-a2295f81d7aa'; -- Makers Mark The Heart Release

-- Wording standardization, not a factual change — matches its five
-- siblings which all say "51%+ Rye" without the trailing "Grain."
UPDATE spirits SET mash_bill = 'Rye Mash — 51%+ Rye'
WHERE spirit_id = '0591a04e-1a89-4696-bd9f-6d7751de8aa3'; -- Buffalo Trace Kosher Straight Rye

-- Apostrophe corrections — real brand spelling, confirmed against each
-- distillery's own materials. Seven Jack Daniel's products were missing
-- the apostrophe their six siblings already had correctly.
UPDATE spirits SET name = 'Jack Daniel''s Coy Hill' WHERE spirit_id = '1bdb8210-c4cb-43cf-bb82-4b503b5187fc';
UPDATE spirits SET name = 'Jack Daniel''s Twice Barreled' WHERE spirit_id = 'ced463af-2638-497d-8e9c-a3650f11cd31';
UPDATE spirits SET name = 'Jack Daniel''s Special Reserve' WHERE spirit_id = '8596441c-53b3-4565-a77b-48caeed56029';
UPDATE spirits SET name = 'Jack Daniel''s 10 yr' WHERE spirit_id = '11b71046-3ca7-4c50-8600-0a2ea3697436';
UPDATE spirits SET name = 'Jack Daniel''s 12 yr' WHERE spirit_id = '6877c396-c35d-406d-81bb-df36fb7d66a7';
UPDATE spirits SET name = 'Jack Daniel''s 14 yr' WHERE spirit_id = 'c63b98a9-d4eb-4719-b96f-b4dc25c66672';
UPDATE spirits SET name = 'Jack Daniel''s Sinatra' WHERE spirit_id = '593cdebc-7f93-44f3-81a2-ead30107fb81';

-- Three Michter's products, same issue.
UPDATE spirits SET name = 'Michter''s Toasted Mash' WHERE spirit_id = 'eda060ed-f5fd-466b-b726-2dc4dd3290d9';
UPDATE spirits SET name = 'Michter''s 10 yr' WHERE spirit_id = '4e857be0-2940-4792-b046-86a44061fe54';
UPDATE spirits SET name = 'Michter''s 10 yr Rye' WHERE spirit_id = 'c2dbfa24-0b69-4351-960a-85da3fbcb8a3';

-- Two Heaven's Door products, same issue.
UPDATE spirits SET name = 'Heaven''s Door Bootleg' WHERE spirit_id = '1ffbc636-34b6-4bb0-ba59-061cc8588958';
UPDATE spirits SET name = 'Heaven''s Door 10 yr' WHERE spirit_id = '84a9726c-4ba0-4c4c-b483-4e58725fd692';

-- One Baker's product, same issue.
UPDATE spirits SET name = 'Baker''s High Rye' WHERE spirit_id = '6bb96546-d4c0-405b-a150-87d7b7515480';

-- Shenk's was attributed to "Limestone Branch Distillery," but the real
-- Shenk's Homestead Sour Mash Whiskey is a Michter's heritage release,
-- same family as the existing Bombergers entry. It also isn't a wheated
-- bourbon in real life — it's an undisclosed rye-forward sour mash blend
-- (sometimes called a "bourye"), so the wheated mash bill text was wrong
-- in kind, not just in numbers.
UPDATE spirits SET distillery = 'Michter''s Distillery (Heritage Label)',
                   mash_bill = 'Sour Mash — Undisclosed Rye-Forward Blend'
WHERE spirit_id = '39eb599f-3c15-4b6a-ac62-ae16c7ad1c0e'; -- Shenks