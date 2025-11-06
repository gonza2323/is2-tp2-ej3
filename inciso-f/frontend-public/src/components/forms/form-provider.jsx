import { createContext, useContext } from 'react';
import { Box, } from '@mantine/core';

const FormContext = createContext({});

export function useForm() {
  const context = useContext(FormContext);
  return context;
}

export function FormProvider({
  children,
  form,
  ...props
}) {
  return (
    <FormContext.Provider value={form}>
      <Box component="form" {...props}>
        {children}
      </Box>
    </FormContext.Provider>
  );
}
