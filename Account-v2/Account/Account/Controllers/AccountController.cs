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
        public ActionResult<Bill> queryBillByOther(int billID, int cateID, string type, float money, string choice)
        {
            BillDal billDal = new BillDal();
            return billDal.QueryFromBillByOther(billID,cateID,type,money,choice);

        }
        [HttpGet]
        public ActionResult<List<Bill>> queryBillByDate(DateTime[] Date)
        {

            BillDal billDal = new BillDal();
            return billDal.QueryFromBillByDate(Date).ToList();
        }
        [HttpPost]
        public ActionResult<long> insertBill(Bill bill)
        {
            BillDal billDal = new BillDal();   
            return billDal.InseryBill(bill);
        }
        [HttpPost]
        public ActionResult<long> insertCategory(Category category)
        {
            CategoryDal categoryDal = new CategoryDal();
            return categoryDal.insertCategory(category);
        }
        [HttpPost]

        [HttpDelete]
        public ActionResult<long> deleteFromBillByID(int id)
        {
            BillDal billDal = new BillDal();
            return billDal.DeleteFromBillByID(id);
        }
        [HttpDelete]
        public ActionResult<long> deleteFromCategoryByName(string name)
        {
            CategoryDal cateDal = new CategoryDal();
            return cateDal.DeleteCategoryByName(name);
        }
        
    }
}
