using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class Context:DbContext

    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Data Source='.\\MSSQLSERVER2019';User ID=hellowor_admin;Password=admin123+_;Database=hellowor_learnenglish");
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Word> Words { get; set; }

    }
}
