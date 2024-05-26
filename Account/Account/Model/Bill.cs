using System;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Account.Model
{
    public class Bill
    {
        public int? BillID { get; set; }
        public string? Type { get; set; }//采用Income与Expense！！！后面逻辑如此使用。
        public DateTime DateTime { get; set; }
        public Category Category { get; set; }
        public float? Money { get; set; }
        public int? CateID { get; set; }
        public string? Remarks { get; set; }

        public Bill(string type, DateTime dateTime, float money, int cateID, string remarks)
        {
            Type = type;
            DateTime = dateTime;
            Money = money;
            CateID = cateID;
            Remarks = remarks;
        }

        public Bill()
        {
        }
        public string toString()
        {
            return "Bill{" +
                    "BillID=" + BillID +
                    ", Type='" + Type + '\'' +
                    ", DateTime=" + DateTime +
                    ", Money=" + Money +
                    ", CateID=" + CateID +
                    ", Remarks='" + Remarks + '\'' +
                    '}';
        }
    }
}
