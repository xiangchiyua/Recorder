using Account.Core;
using Account.Model;
using Microsoft.Data.SqlClient;
using System.Data;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Account.Dal
{
    public class BillDal
    {
        //private static string TABLE_NAME_BILL = "bill";
        //private static string TABLE_NAME_CATEGORY = "category";

        public List<Bill> QueryFromBillByOther(int billID, int cateID, string type, float money, string choice)
        {
            List<Bill> bills = new List<Bill>();
            string sql = null;
            if (choice.Equals("大于等于"))
            {
                sql = "SELECT * FROM bill WHERE billID = @billID AND cateID = @cateID AND type = @type AND money >= @money";
            }
            else if (choice.Equals("小于等于"))
            {
                sql = "SELECT * FROM bill WHERE billID = @billID AND cateID = @cateID AND type = @type AND money <= @money";
            }
            else
            {
                sql = "SELECT * FROM bill WHERE billID = @billID AND cateID = @cateID AND type = @type";
            }
            //SqlHelper sqlHelper = new SqlHelper(); 
            DataRow row = null;
            DataTable res = SqlHelper.ExecuteTable(sql,
                new SqlParameter("@billID", billID), new SqlParameter("@cateID", cateID),new SqlParameter("@type", type),new SqlParameter("@money", money));
            if (res.Rows.Count != 0)
            {
                int num = res.Rows.Count;
                while ((--num)>0)
                {
                    row = res.Rows[num];
                    Bill bill = new Bill();
                    bill.BillID = (int)row["billID"];
                    bill.CateID = (int)row["cateID"];
                    bill.Money = (float)row["money"];
                    bill.Remarks = row["remarks"].ToString();
                    bill.Type = row["type"].ToString();
                    bill.DateTime = (Date)row["dateTime"];
                    bills.Add(bill);
                }
            }
            return bills;
        }

        public List<Bill> QueryFromBillByDate(Date[] date)
        {
            Date startDate = date[0];
            Date endDate = date[1];
            List<Bill> bills = new List<Bill>();
            string sql = "SELECT * FROM bill WHERE dateTime >= @startDate AND dateTime <= @endDate";
            DataRow row = null;
            DataTable res = SqlHelper.ExecuteTable(sql,
                new SqlParameter("@startDate", startDate), new SqlParameter("@endDate", endDate));
            if (res.Rows.Count != 0)
            {
                int num = res.Rows.Count;
                while ((--num) > 0)
                {
                    row = res.Rows[num];
                    Bill bill = new Bill();
                    bill.BillID = (int)row["billID"];
                    bill.CateID = (int)row["cateID"];
                    bill.Money = (float)row["money"];
                    bill.Remarks = row["remarks"].ToString();
                    bill.Type = row["type"].ToString();
                    bill.DateTime = (Date)row["dateTime"];
                    bills.Add(bill);
                }
            }
            return bills;
        }

        public long InseryBill(Bill bill)
        {
            string sql = "INSERT INTO bill(cateID,money,remarks,type,dateTime) VALUES('@cateID','@money','@remarks','@type','@dateTime')";
            return SqlHelper.ExecuteNonQuery(sql,
                new SqlParameter[]
                {
                    new SqlParameter("@cateID",bill.CateID),new SqlParameter("@money",bill.Money),new SqlParameter("@remarks",bill.Remarks),
                    new SqlParameter("@type",bill.Type),new SqlParameter("@dateTime",bill.DateTime)
                });
        }

        public long DeleteFromBillByID(int id)
        {
            string sql = "DELETE FROM bill WHERE billID = @billID";
            return SqlHelper.ExecuteNonQuery(sql,new SqlParameter("@billID",id));
        }
    }
}
