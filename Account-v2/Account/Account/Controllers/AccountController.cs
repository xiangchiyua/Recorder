using Account.Core;
using Account.Model;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Data;
//using static System.Runtime.InteropServices.JavaScript.JSType;
using Account.Dal;

namespace Account.Controllers
{
    [Route("[controller]/[action]")]
    [ApiController]
    public class AccountController : ControllerBase
    {
        [HttpGet]
        public List<Bill> queryBillByOther(int billID, int cateID, string type, float money, string choice)
        {
            BillDal billDal = new BillDal();
            return billDal.QueryFromBillByOther(billID,cateID,type,money,choice);

        }
        [HttpGet]
        public List<Bill> queryBillByDate(DateTime[] Date)
        {

            BillDal billDal = new BillDal();
            return billDal.QueryFromBillByDate(Date);
        }
        [HttpPost]
        public long insertBill(Bill bill)
        {
            BillDal billDal = new BillDal();   
            return billDal.InseryBill(bill);
        }
        [HttpPost]
        public long insertCategory(Category category)
        {
            CategoryDal categoryDal = new CategoryDal();
            return categoryDal.insertCategory(category);
        }
        [HttpPost]

        [HttpDelete]
        public long deleteFromBillByID(int id)
        {
            BillDal billDal = new BillDal();
            return billDal.DeleteFromBillByID(id);
        }
        [HttpDelete]
        public long deleteFromCategoryByName(string name)
        {
            CategoryDal cateDal = new CategoryDal();
            return cateDal.DeleteCategoryByName(name);
        }
        
    }
}
