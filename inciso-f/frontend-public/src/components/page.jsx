import { forwardRef, ReactNode, useEffect } from 'react';
import { Box } from '@mantine/core';
import { app } from '../config';

export const Page = forwardRef(
  ({ children, title = '', meta, ...other }, ref) => {
    // useEffect(() => {
    //   nprogress.complete();
    //   return () => nprogress.start();
    // }, []);

    return (
      <>
        {/* <Helmet>
          <title>{`${title} | ${app.name}`}</title>
          {meta}
        </Helmet> */}

        <Box ref={ref} {...other}>
          {children}
        </Box>
      </>
    );
  }
);
