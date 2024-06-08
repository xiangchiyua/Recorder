using System;
//using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Account.Model
{
    public class BillImage : Bill
    {
        public string? Image { get; set; }
        public BillImage()
        {
        }
        public BillImage(string type, DateTime dateTime, float money, int cateID, string remarks, string image)
        {
            Type = type;
            DateTime = dateTime;
            Money = money;
            CateID = cateID;
            Remarks = remarks;
            Image = image;
        }


    }

}
