-- ============================================================
-- V2 — Add vector embedding column for AI similarity search
-- ============================================================
-- WHY THIS EXISTS:
-- Russ AI's recommendation engine currently matches spirits by
-- exact-string flavor tags. This is limited — "Caramel" only
-- matches "Caramel", missing the fact that "Brown Sugar" tastes
-- nearly identical. Vector embeddings solve this by converting
-- each spirit's flavor profile into a 1536-dimension numeric
-- vector. Similar flavors land close together in vector space,
-- so the engine can find true taste neighbors mathematically.
--
-- DIMENSION CHOICE — 1536:
-- This matches Amazon Titan Text Embeddings V2 (via AWS Bedrock),
-- my chosen embedding model. I commit to this dimension on
-- purpose. If I ever switch embedding models, I will NOT alter
-- this column in place — I will use the expand-contract pattern:
-- add a new embedding_v2 column, backfill it in the background,
-- swap reads over, then drop the old column. Altering a live
-- embedding column is never done in production.
--
-- INDEX CHOICE — HNSW:
-- At 225 spirits Postgres can scan all vectors instantly with no
-- index. I add an HNSW index now anyway because this product is
-- built to scale — multiple restaurant locations with thousands
-- of combined bottles. HNSW keeps similarity search fast as the
-- dataset grows, so I never have to refactor for performance later.
-- vector_cosine_ops = I compare vectors by cosine similarity,
-- the standard distance measure for text embeddings.
-- ============================================================

-- Add the embedding column. Nullable on purpose — existing 225
-- spirits start with no embedding and get backfilled once the
-- Bedrock integration is built. New spirits get their embedding
-- generated automatically by the service layer on creation.
ALTER TABLE spirits
    ADD COLUMN embedding vector(1536);

-- HNSW index for fast approximate nearest-neighbor search at scale.
CREATE INDEX idx_spirits_embedding
    ON spirits
    USING hnsw (embedding vector_cosine_ops);