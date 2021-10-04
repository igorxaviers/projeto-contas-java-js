var valid = false;
var all;
Validations = {

    eventValidations: (elements) => {
        all = elements;
        for(let el of elements){
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
        if(el.value.length <= 1 || el.value < 0 || el.value == '')
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
    },

    validadeAll: () => {
        let valido = true;
        for(let el of all)
            if(el.required && el.value == '')
            {
                console.log(el);
                valido = false;
            }
        return valido;
    },

    isValid: () => {
        return valid && Validations.validadeAll();
    }
}