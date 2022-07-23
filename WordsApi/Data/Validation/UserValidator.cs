using Data.Models;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Validation
{
    public class UserValidator : AbstractValidator<User>
    {
        public UserValidator()
        {
            RuleFor(c => c.FullName).NotNull().NotEmpty().MaximumLength(30).MinimumLength(3).WithMessage("Username is not valid. Please check.");
            RuleFor(c => c.Mail).NotNull().NotEmpty().EmailAddress().WithMessage("Mail is not valid. Please check");
            RuleFor(c => c.Password).NotNull().NotEmpty().MinimumLength(8).WithMessage("Password length must be at least 8")
                    .MaximumLength(16).WithMessage("Password length must not exceed 16")
                    .Matches(@"[A-Z]+").WithMessage("Your password must contain at least one uppercase letter.")
                    .Matches(@"[a-z]+").WithMessage("Your password must contain at least one lowercase letter.")
                    .Matches(@"[0-9]+").WithMessage("Your password must contain at least one number.");
            RuleFor(c => c.Authorization).NotNull().NotEmpty().LessThan(10);
        }
    }
}
