# Russ AI 🥃
### The Intelligent Hospitality Engine

> Russ AI bridges the gap between massive spirit inventories and real-time service expertise! Giving bartenders and servers instant, AI-powered upsell intelligence at the point of order.

---

## The Problem

High-volume hospitality venues carry hundreds of spirits. Staff can't know every mash bill, distillery relationship, or flavor profile. Upsells are missed. Margin is left on the table. Every night.

## The Solution

Russ AI monitors order events and surfaces the right upsell suggestion at the right moment — powered by vector similarity search and a large language model that speaks bartender.

> *"The Buffalo Trace they just ordered shares Mash Bill #1 with Eagle Rare 10yr — same distillery, aged longer, only $6 more. That's your upsell."*

---

## 🚀 Core Features

| Feature | Description |
|---|---|
| **Sommelier Engine** | Vector-based spirit recommendations using RAG (Retrieval-Augmented Generation) across mash bill, flavor profile, distillery, proof, and finish |
| **Russ-Script** | LLM-generated natural-language upsell scripts delivered to front-of-house staff in real time |
| **Slam-Meter** *(roadmap)* | Kitchen and floor latency predictive analytics |
| **Toast POS Integration** *(roadmap)* | Order event hooks that trigger upsell suggestions directly from the POS |

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| **Backend** | Java 21, Spring Boot 3.5 |
| **Database** | PostgreSQL 18 + pgvector |
| **AI / ML** | Amazon Bedrock (Claude Sonnet) |
| **Infrastructure** | AWS Free Tier (EC2, RDS, API Gateway) |
| **Build** | Maven, Flyway Migrations |

---

## 📊 Data Model

Each spirit in the inventory is stored as a structured fingerprint:

```
spirit_id     — UUID primary key
name          — Product name
category      — Bourbon, Rye, Scotch, Tequila, etc.
distillery    — Producing distillery
mash_bill     — Grain recipe (the DNA of a bourbon)
flavor_tags   — Comma-separated tasting notes
proof         — ABV proof
age_statement — Years aged (null = NAS)
batch_type    — Single Barrel, Small Batch, Barrel Proof, etc.
finish        — Secondary cask finish if applicable
price_pour    — Pour price
```

**Current inventory: 225 spirits** seeded across Bourbon, Rye, Scotch, Japanese Whisky, Tequila, Mezcal, Vodka, Gin, and Rum — sourced from The Hangry Bison's full spirit program.

---

## 🗺 Roadmap

- [x] Spring Boot scaffold + PostgreSQL connection
- [x] Flyway migrations + full spirit inventory seed (225 spirits)
- [x] `Spirit.java` JPA entity + REST API (`GET /api/spirits`)
- [ ] pgvector extension + embedding pipeline
- [ ] Similarity search endpoint (`POST /api/recommend`)
- [ ] Amazon Bedrock LLM integration (Russ-Script generation)
- [ ] Toast POS API integration
- [ ] AWS deployment (EC2 + RDS)

---

## 🏃 Running Locally

**Prerequisites:** Java 21+, PostgreSQL, Maven

```bash
# Clone the repo
git clone https://github.com/JoelMaldonado222/RussAi.git
cd RussAi

# Create the database
psql -U postgres -c "CREATE DATABASE russai_db;"

# Configure credentials
# Edit src/main/resources/application.properties

# Run
./mvnw spring-boot:run
```

App starts at `http://localhost:8080`

---

## 📬 Contact

Built by **Joel Maldonado** — Software Engineering Gradute, Valencia College Class of 2026.

[GitHub](https://github.com/JoelMaldonado222)  · Open to junior SWE roles

---

*This project is actively in development. Toast POS integration pending developer program approval.*
