# Cosmos DB

When to embed In general, use embedded data models when:
There are contained relationships between entities. There are one-to-few relationships between entities. There's
embedded data that changes infrequently. There's embedded data that won't grow without bound. There's embedded data that
is queried frequently together.

    Typically denormalized data models provide better read performance.

When not to embed

1. While the rule of thumb in Azure Cosmos DB is to denormalize everything and embed all data into a single item, this
   can lead to some situations that should be avoided.
2. This might be what a post entity with embedded comments would look like if we were modeling a typical blog, or CMS,
   system. The problem with this example is that the comments array is unbounded, meaning that there's no (practical)
   limit to the number of comments any single post can have. This may become a problem as the size of the item could
   grow infinitely large so is a design you should avoid.
3. Another case where embedding data isn't a good idea is when the embedded data is used often across items and will
   change frequently.

        Take this JSON snippet.

        JSON

        Copy
        {
            "id": "1",
            "firstName": "Thomas",
            "lastName": "Andersen",
            "holdings": [
                {
                    "numberHeld": 100,
                    "stock": { "symbol": "zbzb", "open": 1, "high": 2, "low": 0.5 }
                },
                {
                    "numberHeld": 50,
                    "stock": { "symbol": "xcxc", "open": 89, "high": 93.24, "low": 88.87 }
                }
            ]
        }
        This could represent a person's stock portfolio. We have chosen to embed the stock information into each portfolio document.
            In an environment where related data is changing frequently, like a stock trading application, embedding data that changes
            frequently is going to mean that you're constantly updating each portfolio document every time a stock is traded.
        Stock zbzb may be traded many hundreds of times in a single day and thousands of users could have zbzb on their portfolio.
            With a data model like the above we would have to update many thousands of portfolio documents many times every day leading to a system that won't scale well.

In the JSON below we chose to use the example of a stock portfolio from earlier but this time we refer to the stock item
on the portfolio instead of embedding it. This way, when the stock item changes frequently throughout the day the only
document that needs to be updated is the single stock document. An immediate downside to this approach though is if your
application is required to show information about each stock that is held when displaying a person's portfolio; in this
case you would need to make multiple trips to the database to load the information for each stock document. Here we've
made a decision to improve the efficiency of write operations, which happen frequently throughout the day, but in turn
compromised on the read operations that potentially have less impact on the performance of this particular system.
Normalized data models can require more round trips to the server.

In general, use normalized data models when:
Representing one-to-many relationships. Representing many-to-many relationships. Related data changes frequently.
Referenced data could be unbounded.

Data flattening All properties in the root level of your Cosmos DB data will be represented in analytical store as a
column and everything else that is in deeper levels of your document data model will be represented as JSON, also in
nested structures. Nested structures demand extra processing from Azure Synapse runtimes to flatten the data in
structured format, what may be a challenge in big data scenarios. The document below will have only two columns in
analytical store, id and contactDetails. All other data, email and phone, will require extra processing through SQL
functions to be individually read. JSON {
"id": "1",
"contactDetails": [
{"email": "thomas@andersen.com"}, {"phone": "+1 555 555-5555"}
]
}

        The document below will have three columns in analytical store, id, email, and phone. All data is directly accessible as columns.

        JSON
        {
            "id": "1",
            "email": "thomas@andersen.com",
            "phone": "+1 555 555-5555"
        }