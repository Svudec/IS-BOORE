describe('Test 1', () => {
    it('Clicks the link "Sign up"', () => {
      cy.visit('/')
  
      cy.url().should('include', '/login')

      cy.contains('Sign up').click()
    })
  })