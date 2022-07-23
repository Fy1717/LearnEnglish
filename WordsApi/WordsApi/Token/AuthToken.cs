using Data.Models;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;

namespace WordsApi.Token
{
    public class AuthToken
    {
        private readonly IConfiguration configuration;

        public AuthToken(IConfiguration configuration)
        {
            this.configuration = configuration;
        }

        public string CreateToken(User user)
        {
            List<Claim> claims = new List<Claim>
            {
                new Claim(ClaimTypes.Email,user.Mail)
            };
            var key = new SymmetricSecurityKey(System.Text.Encoding.UTF8.GetBytes(configuration.GetSection("AppSettings:Token").Value));
            var cred = new SigningCredentials(key, SecurityAlgorithms.HmacSha512Signature);
            var token = new JwtSecurityToken(claims: claims, audience: configuration.GetSection("AppSettings:validAuidience").Value,issuer : configuration.GetSection("AppSettings:validIssuer").Value, expires: DateTime.Now.AddDays(1), signingCredentials: cred);
            var jwt = new JwtSecurityTokenHandler().WriteToken(token);

            return jwt;
        }
    }
}
