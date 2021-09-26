var valid = false;
Validations = {
    eventValidations: (elements) => {
        console.log('asd');
        for(let el of elements){
            console.log(el);
            if(el.required)
            {
                el.addEventListener('keyup', () => {
                    Validations.validate(el);
                });
                el.addEventListener('change', () => {
                    Validations.validate(el);
                });
                el.addEventListener('focusout', () => {
                    Validations.validate(el);
                });
                el.addEventListener('focusin', () => {
                    Validations.validate(el);
                });
            }
        }
    },
    validate: (el) => {
        if(el.value.length <= 1 || el.value < 0)
        {
            el.classList.add('is-invalid');
            valid = false;
        }
        else
        {
            el.classList.remove('is-invalid');
            el.classList.add('is-valid');
            valid = true;
        }
        console.log(valid);
    },
    isValid: () => {
        return valid;
    }
}