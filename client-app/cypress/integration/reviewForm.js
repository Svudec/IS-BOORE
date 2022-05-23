describe('Test 2', () => {
    it('Login and submit review', () => {
      cy.visit('/login')
    
      cy.get('form').within(() => {
        cy.get('[name="username"]').type('testuser')
        cy.get('[name="password"]').type('test')
      })

      cy.contains('SIGN IN').click()

      cy.contains('Profile').click()

      cy.contains('My Reviews').click()

      cy.contains('ADD').click()

      cy.get('[class="MuiRating-visuallyHidden"][value="4"]').click({force: true})

      cy.get('[class="MuiFormControl-root MuiFormControl-fullWidth css-q8hpuo-MuiFormControl-root"]').click()
      
      cy.contains('It').click()

      cy.get('[name="text"]').type('The book was great!')

      cy.contains('SUBMIT').click()
    })
  })