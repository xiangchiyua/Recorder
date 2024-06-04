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
        private readonly ILogger<AccountController> _logger;

        public AccountController(ILogger<AccountController> logger)
        {
            _logger = logger;
        }
        [HttpGet]
        public ActionResult<Bill> queryBillByOther(int billID, int cateID, string type, float money, string choice)
        {
            try
            {
                BillDal billDal = new BillDal();
                var result =  billDal.QueryFromBillByOther(billID,cateID,type,money,choice);
                return result;
            }
            catch(Exception ex)
            {
                _logger.LogError(ex, "Error querying bill by other");
                return StatusCode(StatusCodes.Status500InternalServerError, "Internal server error");
            }

        }
        [HttpGet]
        public ActionResult<List<Bill>> queryBillByDate(DateTime startDate,string endDate)
        {
            try
            {
                  BillDal billDal = new BillDal();
                  return billDal.QueryFromBillByDate(startDate,endDate).ToList();
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error querying bill by date");
                return StatusCode(StatusCodes.Status500InternalServerError, "Internal server error");
            }
        }
        [HttpPost]
        public ActionResult<long> insertBill(Bill bill)
        {
            try
            {
                BillDal billDal = new BillDal();   
                return billDal.InseryBill(bill);
            }
            catch(Exception ex)
            {
                _logger.LogError(ex, "Error inserting bill");
                return StatusCode(StatusCodes.Status500InternalServerError, "Internal server error");
            }
        }
        [HttpPost]
        public ActionResult<long> insertCategory(Category category)
        {
            try
            {
                CategoryDal categoryDal = new CategoryDal();
                return categoryDal.insertCategory(category);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error inserting category");
                return StatusCode(StatusCodes.Status500InternalServerError, "Internal server error");
            }

        }
        [HttpPost]

        [HttpDelete]
        public ActionResult<long> deleteFromBillByID(int id)
        {
            try
            {
                BillDal billDal = new BillDal();
                return billDal.DeleteFromBillByID(id);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error deleting bill by ID");
                return StatusCode(StatusCodes.Status500InternalServerError, "Internal server error");
            }
        }
        [HttpDelete]
        public ActionResult<long> deleteFromCategoryByName(string name)
        {
            try
            {
                CategoryDal cateDal = new CategoryDal();
                return cateDal.DeleteCategoryByName(name);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error deleting category by name");
                return StatusCode(StatusCodes.Status500InternalServerError, "Internal server error");
            }
        }
        
    }
}
