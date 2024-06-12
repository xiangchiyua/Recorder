using Microsoft.Data.SqlClient;
using System.Data;

namespace Account.Core
{
    public class SqlHelper
    {
        public static string ConnectionString { get; set; } = "server=.;database=Account;uid=sa;pwd=3803Xxya;Encrypt=True;TrustServerCertificate=True;";//连接字符串

        public static DataTable ExecuteTable(string cmdText,params SqlParameter[] sqlParameters)
        {
            using SqlConnection conn = new SqlConnection(ConnectionString);
            conn.Open();
            SqlCommand cmd = new SqlCommand(cmdText, conn);
            cmd.Parameters.AddRange(sqlParameters);
            SqlDataAdapter sda = new SqlDataAdapter(cmd);
            DataSet ds = new DataSet();
            sda.Fill(ds);
            return ds.Tables[0];
        }

        public static int ExecuteNonQuery(string cmdText,params SqlParameter[] sqlParameters)
        {
            using SqlConnection conn = new SqlConnection(ConnectionString);
            conn.Open();
            SqlCommand cmd = new SqlCommand(cmdText, conn);
            cmd.Parameters.AddRange(sqlParameters);
            return cmd.ExecuteNonQuery();

        }
    }
}
