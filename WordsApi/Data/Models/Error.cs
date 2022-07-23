using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class Error
    {
        public string Model { get; set; }
        public List<string> ErrorList { get; set; }
        public string Message { get; set; }
        public Error()
        {
            this.ErrorList= new List<string>();
        }

        public Error(string model,string message) {
            this.ErrorList = new List<string>();
            this.Model = model;
            this.Message = message;
        }
    }
}
