namespace Account.Model
{
    public class Category
    {
        public int? CateID { get; set; }
        public string? Name { get; set; }
        public string? Description { get; set; }

        public Category()
        {
        }

        public Category(int cateID, string name, string description)
        {
            CateID = cateID;
            Name = name;
            Description = description;
        }
    }
}
