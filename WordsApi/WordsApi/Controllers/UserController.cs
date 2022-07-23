using Data.Models;
using Data.Validation;
using FluentValidation.Results;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using WordsApi.Token;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace WordsApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly AuthToken authToken;

        public UserController(IConfiguration configuration)
        {
            this.authToken = new AuthToken(configuration);
        }

        [AllowAnonymous]
        [HttpPost("login")]
        public IActionResult Login(LoginParameters login)
        {
            using (Context context = new Context())
            {

                var user = context.Users.Where(u => u.Mail == login.Mail && u.Password == login.Password).FirstOrDefault();
                if (user != null)
                {
                    return Ok(authToken.CreateToken(user));
                }
                else
                {
                    return BadRequest(new Error("User", "Mail or password is wrong"));
                }
            }
        }

        // GET api/<UserController>/5
        [HttpGet("{id}")]
        [Authorize]
        public IActionResult Get(int id)
        {
            using (Context context = new Context())
            {
                var user = context.Users.Where(x => x.Id == id).FirstOrDefault();

                if (user == null)
                {
                    return NotFound(new Error("User","There is no user"));
                   
                }
                else
                {
                    return Ok(user);
                }
            }
        }

        // POST api/<UserController>
        [HttpPost]
        [Authorize]
        public IActionResult Post(User u)
        {
            using (Context context = new Context())
            {
                UserValidator userValidator = new UserValidator();
                ValidationResult result =  userValidator.Validate(u);
                 
                if (result.IsValid)
                {
                    context.Users.Add(u);
                    context.SaveChanges();

                    return Ok("Done");
                }
                else
                {
                    var response = new Error();
                    response.Model = "User";
                    foreach(var error in result.Errors)
                    {
                        response.ErrorList.Add(error.ErrorMessage);
                    }

                    return BadRequest(response);
                }
            }
        }

        // PUT api/<UserController>/5
        [HttpPut]
        [Authorize]
        public IActionResult Put(User u)
        {
            using (Context context = new Context())
            {
                UserValidator userValidator = new UserValidator();
                ValidationResult result = userValidator.Validate(u);

                if (result.IsValid)
                {
                    context.Users.Update(u);
                    context.SaveChanges();

                    return Ok("Done");
                }
                else
                {
                    var response = new Error();
                    response.Model = "User";
                    foreach (var error in result.Errors)
                    {
                        response.ErrorList.Add(error.ErrorMessage);
                    }

                    return BadRequest(response);
                }
            }
        }

        // DELETE api/<UserController>/5
        [HttpDelete("{id}")]
        [Authorize]
        public IActionResult Delete(int id)
        {
            using (Context context = new Context())
            {
                var user = context.Users.FirstOrDefault(u => u.Id == id);
                if (user == null)
                {
                    return NotFound(new Error("User","User not found"));
                }
                else
                {
                    context.Users.Remove(user);
                    context.SaveChanges();

                    return Ok("Done");
                }
            }
        }
    }
}
