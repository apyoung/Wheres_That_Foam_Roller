CREATE TABLE item (
    UPC INT,
    `Condition` VARCHAR(45),
    MinimumSOQ INT,
    MadeIn VARCHAR(45),
    Name VARCHAR(45),
    PRIMARY KEY (UPC , `Condition`)
);
    
CREATE TABLE itemCategory (
    UPC INT,
    `Condition` VARCHAR(45),
    Category VARCHAR(45),
    PRIMARY KEY (UPC , `Condition` , Category),
    FOREIGN KEY (UPC , `Condition`)
        REFERENCES item (UPC , `Condition`)
        ON UPDATE CASCADE ON DELETE CASCADE
);
    
CREATE TABLE store (
    StoreID INT NOT NULL,
    Name VARCHAR(45),
    Street VARCHAR(45),
    City VARCHAR(45),
    Zip INT,
    State VARCHAR(45),
    Phone VARCHAR(16),
    PRIMARY KEY (StoreID)
);

CREATE TABLE carries (
    UPC INT NOT NULL,
    `Condition` VARCHAR(45) NOT NULL,
    StoreID INT NOT NULL,
    InventoryOnOrder INT,
    Price DECIMAL(10 , 2 ),
    InventoryLevel INT,
    LastStockedDate DATE,
    PRIMARY KEY (UPC , `Condition` , StoreID),
    FOREIGN KEY (UPC , `Condition`)
        REFERENCES item (UPC , `Condition`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (StoreID)
        REFERENCES store (StoreID)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE company (
    Name VARCHAR(45) NOT NULL,
    PRIMARY KEY (Name)
);

CREATE TABLE owns (
    CompanyName VARCHAR(45) NOT NULL,
    StoreID INT NOT NULL,
    PRIMARY KEY (CompanyName , StoreID),
    FOREIGN KEY (StoreID)
        REFERENCES store (StoreID)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (CompanyName)
        REFERENCES company (Name)
        ON UPDATE CASCADE ON DELETE CASCADE
);
        
CREATE TABLE reviewer (
    Username VARCHAR(45) NOT NULL,
    PRIMARY KEY (Username)
);
        
CREATE TABLE review (
    ReviewID INT NOT NULL,
    Content VARCHAR(999),
    Rating INT,
    ReviewerName VARCHAR(45),
    PRIMARY KEY (ReviewID),
    FOREIGN KEY (ReviewerName)
        REFERENCES reviewer (UserName)
);
    
CREATE TABLE itemReview (
    ReviewID INT,
    UPC INT NOT NULL,
    `Condition` VARCHAR(45) NOT NULL,
    PRIMARY KEY (ReviewID),
    FOREIGN KEY (ReviewID)
        REFERENCES review (ReviewID)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (UPC , `Condition`)
        REFERENCES item (UPC , `Condition`)
);
    
CREATE TABLE storeReview (
    ReviewID INT NOT NULL,
    StoreID INT NOT NULL,
    PRIMARY KEY (ReviewID),
    FOREIGN KEY (ReviewID)
        REFERENCES review (ReviewID)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (StoreID)
        REFERENCES store (StoreID)
);