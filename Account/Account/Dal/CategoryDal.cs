using Account.Core;
using Account.Model;
using Microsoft.Data.SqlClient;

namespace Account.Dal
{
    public class CategoryDal
    {
        public long insertCategory(Category category)
        {
            string sql = "INSERT INTO category(name,description) VALUES('@name','@description')";
            return SqlHelper.ExecuteNonQuery(sql,
                new SqlParameter[]
                {
                    new SqlParameter("@name",category.Name),new SqlParameter("@description",category.Description),
                });
        }

        public long DeleteCategoryByName(string name)
        {
            string sql = "DELETE FROM category WHERE name = @name";
            return SqlHelper.ExecuteNonQuery(sql, new SqlParameter("@name", name));
        }
    }
    
}
