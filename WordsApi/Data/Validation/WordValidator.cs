using Data.Models;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Validation
{
    public class WordValidator : AbstractValidator<Word>
    {
        public WordValidator()
        {
            RuleFor(c => c.English).NotNull().NotEmpty().WithMessage("Word should not empty").MaximumLength(30);
            RuleFor(c => c.Turkish).NotNull().NotEmpty().WithMessage("Word should not empty").MaximumLength(30);
        }
    }
}
