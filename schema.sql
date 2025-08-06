-- =====================================================
-- USER MANAGEMENT TABLES
-- =====================================================

-- System administrators
CREATE TABLE admins (
    id INT NOT NULL AUTO_INCREMENT,
    admin_id VARCHAR(36) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) DEFAULT NULL,
    email VARCHAR(50) NOT NULL,
    telephone VARCHAR(20) DEFAULT NULL,
    photo TEXT,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'active',
    dial_code VARCHAR(5) NOT NULL DEFAULT '233',
    iso_code VARCHAR(2) NOT NULL DEFAULT 'GH',
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY unique_admin_id (admin_id),
    UNIQUE KEY unique_admin_email (email)
);

-- Authenticated users
CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    account_type INT NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(500) NOT NULL,
    picture_path VARCHAR(255) DEFAULT NULL,
    status VARCHAR(115) DEFAULT 'active',
    created_at DATETIME NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_user_id (user_id),
    UNIQUE KEY unique_email (email)
);

-- System users
CREATE TABLE sys_users (
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    account_type INT NOT NULL,
    status VARCHAR(115) DEFAULT 'active',
    created_at DATETIME NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_staff_user_id (user_id)
);

-- =====================================================
-- CLIENT MANAGEMENT TABLES
-- =====================================================

-- Client organizations
CREATE TABLE clients (
    id INT NOT NULL AUTO_INCREMENT,
    client_id VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    postal_address VARCHAR(255) NOT NULL,
    physical_address VARCHAR(255) DEFAULT NULL,
    website VARCHAR(255) DEFAULT NULL,
    phone VARCHAR(255) NOT NULL,
    contact_person VARCHAR(255) NOT NULL,
    expiry_date DATETIME DEFAULT NULL,
    description VARCHAR(255) DEFAULT NULL,
    profile_picture VARCHAR(255) DEFAULT NULL,
    status VARCHAR(115) DEFAULT 'active',
    created_at DATETIME NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_client_id (client_id)
);

-- Users associated with clients
CREATE TABLE client_users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    client_id VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) DEFAULT NULL,
    status INT DEFAULT NULL,
    created_by VARCHAR(255) DEFAULT NULL,
    updated_by VARCHAR(255) DEFAULT NULL,
    created_at VARCHAR(255) NOT NULL,
    updated_at VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- =====================================================
-- SUBSCRIPTION & BILLING TABLES
-- =====================================================

-- Available packages
CREATE TABLE packages (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    package_id VARCHAR(256) NOT NULL,
    name VARCHAR(100) NOT NULL,
    sub_heading TEXT,
    price_monthly DECIMAL(10,2) NOT NULL DEFAULT '0.00',
    price_annually DECIMAL(10,2) NOT NULL DEFAULT '0.00',
    currency TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- Package feature details
CREATE TABLE package_details (
    id INT NOT NULL AUTO_INCREMENT,
    detail_id VARCHAR(50) NOT NULL,
    package_id VARCHAR(50) NOT NULL,
    content TEXT,
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- User subscriptions
CREATE TABLE subscriptions (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    subscription_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    package_id VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL DEFAULT '0.00',
    billing_date DATE NOT NULL,
    expiry_date DATE NOT NULL,
    cycle VARCHAR(45) NOT NULL,
    channel VARCHAR(45) DEFAULT NULL,
    status VARCHAR(45) DEFAULT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(45) NOT NULL,
    updated_by VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- Subscription assignments
CREATE TABLE subscribers (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    subscriber_id VARCHAR(255) NOT NULL,
    subscription_id VARCHAR(255) NOT NULL,
    package_id VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(45) DEFAULT NULL,
    updated_by VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- Payment transactions
CREATE TABLE transactions (
    id INT NOT NULL AUTO_INCREMENT,
    reference VARCHAR(255) NOT NULL,
    access_code VARCHAR(255) NOT NULL,
    authorization_url VARCHAR(255) DEFAULT NULL,
    amount VARCHAR(45) DEFAULT NULL,
    package VARCHAR(45) NOT NULL,
    cycle VARCHAR(45) DEFAULT NULL,
    user_id VARCHAR(255) NOT NULL,
    status VARCHAR(255) DEFAULT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(255) DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- =====================================================
-- MEDIA HOUSE TABLES
-- =====================================================

-- Print media houses
CREATE TABLE print_media_houses (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    status INT DEFAULT '1',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- TV media houses
CREATE TABLE tv_media_houses (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    status INT DEFAULT '1',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- Radio media houses
CREATE TABLE radio_media_houses (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    status INT DEFAULT '1',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (id)
);

-- Web media houses
CREATE TABLE web_media_houses (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    status INT DEFAULT '1',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- =====================================================
-- MEDIA CONTENT TABLES
-- =====================================================

-- Main media registry
CREATE TABLE media (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) DEFAULT NULL,
    media_type INT NOT NULL,
    media_id VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL,
    status INT DEFAULT '0',
    created_by VARCHAR(255) DEFAULT NULL,
    updated_by VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_media_id (media_id)
);

-- Print media articles
CREATE TABLE print_articles (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    page_number INT DEFAULT NULL,
    publication VARCHAR(255) DEFAULT NULL,
    issue_number VARCHAR(255) DEFAULT NULL,
    summary VARCHAR(1655) DEFAULT NULL,
    keywords VARCHAR(500) DEFAULT NULL,
    content LONGTEXT,
    image_path VARCHAR(255) DEFAULT NULL,
    industry VARCHAR(255) DEFAULT NULL,
    sub_industry VARCHAR(255) DEFAULT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    media_id VARCHAR(255) NOT NULL,
    status INT NOT NULL DEFAULT '0',
    PRIMARY KEY (id),
    UNIQUE KEY unique_media_id (media_id)
);

-- TV stories/segments
CREATE TABLE tv_stories (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    channel VARCHAR(45) DEFAULT NULL,
    presenters VARCHAR(500) DEFAULT NULL,
    show_name VARCHAR(255) DEFAULT NULL,
    aired_date DATETIME DEFAULT NULL,
    summary VARCHAR(1655) DEFAULT NULL,
    keywords VARCHAR(500) DEFAULT NULL,
    video_url VARCHAR(255) DEFAULT NULL,
    video_title VARCHAR(255) DEFAULT NULL,
    industry VARCHAR(255) DEFAULT NULL,
    sub_industry VARCHAR(255) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL,
    status INT DEFAULT '0',
    media_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_media_id (media_id)
);

-- Radio stories/segments
CREATE TABLE radio_stories (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    frequency VARCHAR(255) NOT NULL,
    presenters VARCHAR(500) DEFAULT NULL,
    show_name VARCHAR(255) DEFAULT NULL,
    aired_date DATETIME DEFAULT NULL,
    summary VARCHAR(1655) DEFAULT NULL,
    keywords VARCHAR(255) DEFAULT NULL,
    audio_title VARCHAR(45) DEFAULT NULL,
    audio_path VARCHAR(255) NOT NULL,
    industry VARCHAR(255) DEFAULT NULL,
    sub_industry VARCHAR(255) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL,
    status INT DEFAULT '0',
    media_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_media_id (media_id)
);

-- Web articles
CREATE TABLE web_articles (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication VARCHAR(255) DEFAULT NULL,
    published_date DATETIME DEFAULT NULL,
    source_url VARCHAR(255) DEFAULT NULL,
    source_title VARCHAR(255) DEFAULT NULL,
    summary VARCHAR(500) DEFAULT NULL,
    keywords VARCHAR(1650) DEFAULT NULL,
    image_path VARCHAR(255) DEFAULT NULL,
    content LONGTEXT,
    industry VARCHAR(255) DEFAULT NULL,
    sub_industry VARCHAR(255) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL,
    status INT DEFAULT '0',
    media_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_media_id (media_id)
);

-- =====================================================
-- INDUSTRY & CATEGORIZATION TABLES
-- =====================================================

-- Industry categories
CREATE TABLE industries (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    status INT DEFAULT '1',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (id)
);

-- User-defined tags
CREATE TABLE tags (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(105) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

-- =====================================================
-- USER PREFERENCES & NOTIFICATIONS
-- =====================================================

-- User news preferences
CREATE TABLE news_preferences (
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(225) NOT NULL,
    email_notifications INT NOT NULL DEFAULT '0',
    sms_notifications INT NOT NULL DEFAULT '0',
    keywords VARCHAR(1650) NOT NULL,
    industry VARCHAR(255) NOT NULL,
    sub_industries VARCHAR(255) NOT NULL,
    status INT NOT NULL DEFAULT '0',
    created_at DATETIME NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_user_id (user_id)
);

-- Email dispatch tracking
CREATE TABLE email_dispatches (
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(255) NOT NULL,
    token VARCHAR(45) NOT NULL,
    status VARCHAR(155) DEFAULT NULL,
    sent_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    viewed_date DATETIME DEFAULT NULL,
    PRIMARY KEY (id)
);

-- =====================================================
-- DEMO & INQUIRY TABLES
-- =====================================================

-- Demo requests
CREATE TABLE demo_requests (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    phone VARCHAR(45) NOT NULL,
    email VARCHAR(115) NOT NULL,
    enquiry_type VARCHAR(1005) NOT NULL,
    message TEXT,
    created_at DATETIME NOT NULL,
    status VARCHAR(255) DEFAULT 'requested',
    PRIMARY KEY (id)
);

-- =====================================================
-- ANALYTICS & LOGGING TABLES
-- =====================================================

-- User activity logs
CREATE TABLE activity_logs (
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(255) NOT NULL,
    username VARCHAR(255) DEFAULT 'N/A',
    activity VARCHAR(255) DEFAULT NULL,
    page_url VARCHAR(255) NOT NULL,
    ip_address VARCHAR(255) DEFAULT NULL,
    mac_address VARCHAR(255) DEFAULT NULL,
    device VARCHAR(255) DEFAULT NULL,
    browser VARCHAR(255) DEFAULT NULL,
    operating_system VARCHAR(255) DEFAULT NULL,
    request_method VARCHAR(255) DEFAULT NULL,
    protocol VARCHAR(45) DEFAULT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    status INT DEFAULT NULL,
    PRIMARY KEY (id)
);

-- Article view tracking
CREATE TABLE view_logs (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(36) NOT NULL,
    source_id VARCHAR(36) NOT NULL,
    article_id VARCHAR(36) NOT NULL,
    reporter_id VARCHAR(36) DEFAULT NULL,
    ip_address VARCHAR(50) DEFAULT NULL,
    mac_address VARCHAR(50) DEFAULT NULL,
    device VARCHAR(50) DEFAULT NULL,
    browser VARCHAR(50) DEFAULT NULL,
    operating_system VARCHAR(50) DEFAULT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- =====================================================
-- UTILITY TABLES
-- =====================================================

-- Number sequence helper table
CREATE TABLE numbers (
    n INT DEFAULT NULL
);
