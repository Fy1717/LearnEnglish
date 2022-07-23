using Data.Models;
using Data.Validation;
using FluentValidation.Results;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace WordsApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize]
    public class WordController : ControllerBase
    {
        // GET: api/<WordController>
        [HttpGet]
        public IActionResult Get()
        {
            using (Context context = new Context())
            {
                var words = context.Words.ToList();

                if (words.Count > 0) return Ok(words);
                else
                {
                    return NotFound(new Error("Word","There is no word"));
                }
            }
        }

        // GET api/<WordController>/5
        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            using (Context context = new Context())
            {
                var word = context.Words.Where(w => w.Id == id).FirstOrDefault();

                if (word == null) return NotFound(new Error("Word","There is no word"));
                else
                {
                    return Ok(word);
                }
            }
        }

        // POST api/<WordController>
        [HttpPost]
        public IActionResult Post(Word w)
        {
            using (Context context = new Context())
            {
                WordValidator userValidator = new WordValidator();
                ValidationResult result = userValidator.Validate(w);

                if (result.IsValid)
                {
                    context.Words.Add(w);
                    context.SaveChanges();

                    return Ok("Done");
                }
                else
                {
                    var response = new Error();
                    response.Model = "Word";
                    foreach (var error in result.Errors)
                    {
                        response.ErrorList.Add(error.ErrorMessage);
                    }

                    return BadRequest(response);
                }
            }
        }

        // PUT api/<WordController>/5
        [HttpPut]
        public IActionResult Put(Word w)
        {
            using (Context context = new Context())
            {
                WordValidator userValidator = new WordValidator();
                ValidationResult result = userValidator.Validate(w);

                if (result.IsValid)
                {
                    context.Words.Add(w);
                    context.SaveChanges();

                    return Ok("Done");
                }
                else
                {
                    var response = new Error();
                    response.Model = "Word";
                    foreach (var error in result.Errors)
                    {
                        response.ErrorList.Add(error.ErrorMessage);
                    }

                    return BadRequest(response);
                }
            }
        }

        // DELETE api/<WordController>/5
        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            using (Context context = new Context())
            {
                var user = context.Users.FirstOrDefault(u => u.Id == id);
                if (user == null)
                {
                    return NotFound(new Error("Word","Word Not Found"));
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
