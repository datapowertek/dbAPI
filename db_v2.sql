-- ==========================================================
-- 1. CLASSIFICATION & HIERARCHY
-- ==========================================================
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    parent_id INT REFERENCES categories(id) ON DELETE SET NULL,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

-- ==========================================================
-- 2. PRODUCT & SPEC TEMPLATES
-- ==========================================================
CREATE TABLE spec_groups (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    display_order INT DEFAULT 0
);

CREATE TABLE product_templates (
    id SERIAL PRIMARY KEY,
    category_id INT NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
    template_name VARCHAR(255) NOT NULL
);

CREATE TABLE spec_definitions (
    id SERIAL PRIMARY KEY,
    template_id INT NOT NULL REFERENCES product_templates(id) ON DELETE CASCADE,
    group_id INT REFERENCES spec_groups(id) ON DELETE SET NULL,
    label VARCHAR(255) NOT NULL,
    data_type VARCHAR(50) CHECK (data_type IN ('text', 'numeric', 'boolean')),
    unit_of_measure VARCHAR(20),
    is_filterable BOOLEAN DEFAULT TRUE,
    display_order INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

-- ==========================================================
-- 3. PRODUCTS, VARIANTS & RELATIONS
-- ==========================================================
CREATE TABLE brands (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE product_masters (
    id SERIAL PRIMARY KEY,
    brand_id INT NOT NULL REFERENCES brands(id) ON DELETE RESTRICT,
    template_id INT NOT NULL REFERENCES product_templates(id) ON DELETE RESTRICT,
    model_name VARCHAR(500) NOT NULL,
    slug VARCHAR(500) UNIQUE NOT NULL,
    marketing_description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

CREATE TABLE product_relations (
    id SERIAL PRIMARY KEY,
    master_id INT NOT NULL REFERENCES product_masters(id) ON DELETE CASCADE,
    related_master_id INT NOT NULL REFERENCES product_masters(id) ON DELETE CASCADE,
    relation_type VARCHAR(50) NOT NULL,
    CONSTRAINT no_self_relation CHECK (master_id <> related_master_id)
);

CREATE TABLE product_variants (
    id SERIAL PRIMARY KEY,
    product_master_id INT NOT NULL REFERENCES product_masters(id) ON DELETE CASCADE,
    sku_code VARCHAR(255) UNIQUE NOT NULL,
    mpn VARCHAR(100),            
    upc_ean VARCHAR(20),         
    display_title VARCHAR(1000),
    cost_price NUMERIC(12,2) NOT NULL,
    min_price NUMERIC(12,2) NOT NULL,
    market_price NUMERIC(12,2) NOT NULL,
    quantity INT DEFAULT 0,
    min_stock_level INT DEFAULT 5,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

-- ==========================================================
-- 4. VALUES, MEDIA & TABS
-- ==========================================================
CREATE TABLE variant_spec_values (
    variant_id INT NOT NULL REFERENCES product_variants(id) ON DELETE CASCADE,
    spec_id INT NOT NULL REFERENCES spec_definitions(id) ON DELETE CASCADE,
    raw_value_text TEXT NOT NULL,
    normalized_value_num NUMERIC(18,4),
    PRIMARY KEY (variant_id, spec_id)
);

CREATE TABLE product_media (
    id SERIAL PRIMARY KEY,
    variant_id INT REFERENCES product_variants(id) ON DELETE CASCADE,
    media_url TEXT NOT NULL,
    media_type VARCHAR(50) DEFAULT 'image',
    is_primary BOOLEAN DEFAULT FALSE,
    sort_order INT DEFAULT 0
);

CREATE TABLE product_tabs (
    id SERIAL PRIMARY KEY,
    product_master_id INT REFERENCES product_masters(id) ON DELETE CASCADE,
    tab_title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    sort_order INT DEFAULT 0
);

-- ==========================================================
-- 5. PERFORMANCE INDEXES
-- ==========================================================
CREATE INDEX idx_relations_master ON product_relations(master_id);
CREATE INDEX idx_variants_mpn ON product_variants(mpn);
CREATE INDEX idx_variants_master ON product_variants(product_master_id);
CREATE INDEX idx_spec_values_numeric ON variant_spec_values(spec_id, normalized_value_num);
CREATE INDEX idx_variants_search ON product_variants USING GIST (to_tsvector('english', display_title));